package com.scurab.appsandbox.core.android.view

import android.view.View
import androidx.lifecycle.*
import com.scurab.appsandbox.core.android.BaseViewModel
import java.util.concurrent.atomic.AtomicInteger

/**
 * Implementation of multibinding of
 * PrograssBar <-> ViewModel's lifecycle
 */
class ProgressBarMultiHandler(private val invisible: Int = View.GONE) {

    private var binding: Binding? = null
    private val map: MutableMap<LifecycleOwner, Binding> = mutableMapOf()
    val isProgressBarVisible: Boolean
        get() =
            binding?.isProgressBarVisible
                ?: throw IllegalStateException("Binding is null, you have to call bind() first!")

    /**
     * Create a binding between progressBar and viewModel
     *
     * @param progressBar
     * @param viewModel
     * @param lifecycleOwner - view model's lifecycle, mostlikely fragment/activity (not fragment view's)
     */
    fun bind(
        progressBar: View,
        viewModel: BaseViewModel,
        lifecycleOwner: LifecycleOwner
    ): ProgressBarMultiHandler {
        check(!map.containsKey(lifecycleOwner)) {
            "LifecycleOwner $lifecycleOwner already has created binding"
        }
        map[lifecycleOwner] = Binding(progressBar, viewModel, lifecycleOwner, invisible)
        return this
    }

    /**
     * Cancel binding
     */
    fun cancel(lifecycleOwner: LifecycleOwner?) {
        if (lifecycleOwner != null) {
            map.remove(lifecycleOwner)?.cancel()
        } else {
            map.forEach { (_, v) -> v.cancel() }
            map.clear()
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    internal inner class Binding(
        internal val progressBar: View,
        internal val viewModel: BaseViewModel,
        internal val viewModelLifecycleOwner: LifecycleOwner,
        private val invisible: Int
    ) {
        private var lifecycleObserver = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(owner: LifecycleOwner) {
                cancel(owner)
            }
        }
        private val atomicCounter = AtomicInteger()
        private var progressBarObserver = Observer<Boolean> { isProgressBarVisible = it }

        var isProgressBarVisible: Boolean = false
            get() = progressBar.visibility == View.VISIBLE
            set(value) {
                if (field != value) {
                    val counter = if (value) {
                        atomicCounter.incrementAndGet()
                    } else {
                        atomicCounter.decrementAndGet()
                    }
                    progressBar.visibility = if (counter != 0) View.VISIBLE else invisible
                    field = value
                }
            }

        fun cancel() {
            viewModel.progressBar.removeObserver(progressBarObserver)
            viewModelLifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }

        init {
            viewModelLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
            viewModel.progressBar.observeForever(progressBarObserver)
        }
    }
}