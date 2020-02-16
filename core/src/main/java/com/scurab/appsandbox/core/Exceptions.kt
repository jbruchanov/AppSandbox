package com.scurab.appsandbox.core

typealias NPE = NullPointerException
typealias ISE = IllegalStateException
typealias IAE = IllegalArgumentException

fun npe(msg: String): Nothing = throw NPE(msg)
fun ise(msg: String): Nothing = throw ISE(msg)
fun iae(msg: String): Nothing = throw IAE(msg)