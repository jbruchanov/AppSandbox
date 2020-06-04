package com.scurab.appsandbox.core.android.data

import androidx.lifecycle.LifecycleOwner


interface LiveQueue<T> {
    val hasObserver: Boolean
    fun observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit)
    fun removeObserver()
}