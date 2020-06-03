package com.scurab.appsandbox.core.ext

import com.scurab.appsandbox.core.InvalidLifecycleException

/**
 * Simple reference getter for nullable variable, crashing with [NullPointerException] if the reference is null
 */
val <T> T?.ref get() : T = this ?: throw InvalidLifecycleException("Reference is null")