package com.scurab.appsandbox.core.collection

/**
 * Iterate an array in reversed order
 */
inline fun <T> Array<out T>.forEachReversed(action: (T) -> Unit) {
    for (i in (size - 1 downTo 0)) action(this[i])
}