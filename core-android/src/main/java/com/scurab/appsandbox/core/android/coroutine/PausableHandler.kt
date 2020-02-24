package com.scurab.appsandbox.core.android.coroutine

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * Special implementation of Android handler to postponed messaged if the handler is paused
 */
internal class PausableHandler(looper: Looper, resumed: Boolean = true) : Handler(looper) {
    private val queue = mutableListOf<Runnable>()

    var isResumed: Boolean = resumed
        private set

    /**
     * Set if the handler should be paused.
     * In case of resuming handler, it triggers enqueued messaged
     *
     * Returns number of executed messages in case of resuming
     */
    internal fun setResumed(resumed: Boolean): Int {
        val diff = isResumed != resumed
        //assign before dispatch
        isResumed = resumed
        return if (resumed && diff) {
            dispatchCoroutines()
        } else 0
    }

    private fun dispatchCoroutines(): Int {
        return if (queue.isNotEmpty()) {
            val toList = queue.toList()
            queue.clear()
            toList.forEach { dispatchMessage(Message.obtain(this, it)) }
            toList.size
        } else 0
    }

    override fun dispatchMessage(msg: Message) {
        if (isResumed) {
            super.dispatchMessage(msg)
        } else {
            queue.add(msg.callback)
        }
    }

    fun clearQueue() {
        queue.clear()
    }
}