package com.scurab.appsandbox.core.android.data

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.scurab.appsandbox.core.android.arch.ITaskScheduler
import com.scurab.appsandbox.core.android.arch.TaskScheduler

/**
 * Similar class to [androidx.lifecycle.LiveData].
 * Main differences are:
 * - can hold only 1 observer => updates might be handled only 1 times
 * - there is a queue of updates => values or not overwritten if [Lifecycle] is not at least [Lifecycle.Event.ON_START]
 * - there is no specific backpressure on the queue, by default it has limit of 8, if the limit is reached,
 * it's throwing [IllegalStateException]
 */
open class MutableLiveQueue<T>(
    initialValue: T? = null,
    private val capacity: Int = 8,
    private val scheduler: ITaskScheduler = TaskScheduler.Instance
) : LiveQueue<T> {

    var isActive = false; private set
    override val hasObserver: Boolean get() = observerWrapper != null

    private val lock = Any()
    private val queue = ArrayList<T>(capacity)
    private var observerWrapper: ObserverWrapper? = null

    init {
        if (initialValue != null) {
            enqueueOrDispatch(initialValue) { _, _ ->
                throw IllegalStateException(
                    "Dispatch in this time shouldn't ever happened," +
                            "as it's ctor call and there shouldn't be any observer attached"
                )
            }
        }
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        synchronized(lock) {
            observerWrapper?.clear()
            observerWrapper = ObserverWrapper(lifecycleOwner, observer)
        }
    }

    override fun removeObserver() {
        if (observerWrapper != null) {
            synchronized(lock) {
                observerWrapper?.clear()
                observerWrapper = null
            }
        }
    }

    private fun onHandleLifecycleEvent(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                isActive = true
                dispatchEvents()
            }
            Lifecycle.Event.ON_STOP -> {
                isActive = false
            }
            Lifecycle.Event.ON_DESTROY -> {
                removeObserver()
            }
        }
    }

    private fun dispatchEvents() {
        val items: List<T>
        val observer: ((T) -> Unit)
        synchronized(lock) {
            val wrapper = observerWrapper
            if (queue.isEmpty() || wrapper == null) {
                return
            }
            observer = wrapper.observer
            items = queue.toList()
            queue.clear()
        }
        items.forEach {
            observer.invoke(it)
        }
    }

    /**
     * Set value
     */
    @MainThread
    fun setValue(item: T) {
        check(scheduler.isMainThread) {
            "setValue is allowed to be executed only in main thread, it's:${Thread.currentThread()}"
        }
        enqueueOrDispatch(item) { observer, v ->
            observer.invoke(v)
        }
    }

    /**
     * Post value
     */
    @AnyThread
    fun postValue(item: T) {
        enqueueOrDispatch(item) { observer, v ->
            scheduler.postToMainThread { observer.invoke(v) }
        }
    }

    private fun enqueueOrDispatch(item: T, dispatch: ((T) -> Unit, T) -> Unit) {
        val observer: (T) -> Unit
        synchronized(lock) {
            val wrapper = observerWrapper
            if (!isActive || wrapper == null) {
                check(queue.size < capacity) {
                    "Already enqueued max amount of $capacity items."
                }
                queue.add(item)
                return
            }
            observer = wrapper.observer
        }
        dispatch(observer, item)
    }

    private inner class ObserverWrapper(
        private val lifecycleOwner: LifecycleOwner,
        val observer: (T) -> Unit
    ) : LifecycleObserver {
        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onAny(source: LifecycleOwner, event: Lifecycle.Event) {
            onHandleLifecycleEvent(source, event)
        }

        fun clear() {
            lifecycleOwner.lifecycle.removeObserver(this)
        }
    }
}