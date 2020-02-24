package com.scurab.appsandbox.core

/**
 * Simple reference getter for nullable variable, crashing with [NullPointerException] if the reference is null
 */
val <T> T?.ref get() : T = this ?: npe("Reference is null, most likely invalid lifecycle access!")