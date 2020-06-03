package com.scurab.appsandbox.core.ext

import java.io.PrintWriter
import java.io.StringWriter

fun Throwable.stackTrace(): String {
    return StringWriter().apply {
        printStackTrace(PrintWriter(this))
    }.toString()
}

fun Throwable.messageWithClassName(): String {
    return javaClass.name + "\n" + (message ?: "Null Err Message")
}