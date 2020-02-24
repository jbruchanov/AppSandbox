package com.scurab.appsandbox.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

private object UNINITIALIZED_VALUE

fun <T> lifecycleLazy(lifecycleProvider: () -> Lifecycle, initializer: () -> T) = LifecycleLazy(
    lifecycleProvider,
    initializer
)

/**
 * Lazy implementation with respect of lifecycle
 *
 * @param lifecycleProvider lambda is necessary, because [Lifecycle] doesn't have to be defined at
 * class instantiation time (e.g. fragment's view lifecycle provider)
 * @param initializer
 */
class LifecycleLazy<out T>(private val lifecycleProvider: () -> Lifecycle,
                           private val initializer: () -> T) : Lazy<T> {
    private var _value: Any? = UNINITIALIZED_VALUE
    private val lifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            reset()
        }
    }

    override val value: T
        get() {
            if (_value === UNINITIALIZED_VALUE) {
                lifecycleProvider().addObserver(lifecycleObserver)
                _value = initializer()
            }
            @Suppress("UNCHECKED_CAST")
            return _value as T
        }

    override fun isInitialized(): Boolean = _value !== UNINITIALIZED_VALUE

    override fun toString(): String =
        if (isInitialized()) value.toString() else "Lazy value not initialized yet."

    fun reset() {
        _value = UNINITIALIZED_VALUE
        lifecycleProvider().removeObserver(lifecycleObserver)
    }
}