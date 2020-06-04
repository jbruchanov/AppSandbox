package com.scurab.appsandbox.core.android.arch

import android.os.Handler
import android.os.Looper

interface ITaskScheduler {
    val isMainThread: Boolean
    fun run(runnable: () -> Unit)
    fun postToMainThread(runnable: () -> Unit)
}

class TaskScheduler private constructor() : ITaskScheduler {
    private val handler = Handler(Looper.getMainLooper())
    override val isMainThread: Boolean get() = Looper.getMainLooper() == Looper.myLooper()

    override fun run(runnable: () -> Unit) {
        runnable.invoke()
    }

    override fun postToMainThread(runnable: () -> Unit) {
        handler.post(runnable)
    }

    companion object {
        val Instance = TaskScheduler()
    }
}