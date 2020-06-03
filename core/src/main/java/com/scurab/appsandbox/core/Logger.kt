package com.scurab.appsandbox.core

import com.scurab.appsandbox.core.ext.stackTrace
import java.text.SimpleDateFormat
import java.util.*

abstract class Logger {
    abstract val isEnabled: Boolean
    abstract val error: (String, String) -> Unit
    abstract val debug: (String, String) -> Unit

    inline fun d(tag: String, msg: () -> String) {
        if (isEnabled) {
            debug.invoke(tag, msg())
        }
    }

    inline fun e(tag: String, msg: () -> String) {
        if (isEnabled) {
            error.invoke(tag, msg())
        }
    }

    fun e(tag: String, ex: Throwable?, msg: (() -> String)?) {
        msg?.let { e(tag) { it() } }
        ex?.let {
            e(tag) { it.message ?: "NullMessage" }
            e(tag) { "\n${it.stackTrace()}" }
        }
    }
}

class JavaLogger : Logger() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    override val isEnabled: Boolean = true
    override val error: (String, String) -> Unit = { tag, msg -> print("D", tag, msg) }
    override val debug: (String, String) -> Unit = { tag, msg -> print("E", tag, msg) }

    private fun print(type: String, tag: String, msg: String) {
        val time = dateFormat.format(Date())
        println("$type-[$time]:$tag $msg")
    }
}