package com.scurab.appsandbox.core

typealias NPE = NullPointerException

fun npe(msg: String): Nothing = throw NPE(msg)


open class InvalidLifecycleException(msg: String) : IllegalStateException(msg)