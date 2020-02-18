package com.scurab.appsandbox.core

import java.io.PrintWriter
import java.io.StringWriter

typealias NPE = NullPointerException

fun npe(msg: String): Nothing = throw NPE(msg)

fun Throwable.stackTrace(): String {
    return StringWriter().apply {
        printStackTrace(PrintWriter(this))
    }.toString()
}

fun Throwable.messageWithClassName(): String {
    return javaClass.name + "\n" + (message ?: "Null Err Message")
}