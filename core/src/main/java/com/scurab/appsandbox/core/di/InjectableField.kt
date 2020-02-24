package com.scurab.appsandbox.core.di

/**
 * Abstract class for reusing common template if case of Injectable field using class delegation
 */
abstract class InjectableField<T>(private val name: String) {
    private var _value: T? = null
    protected val value: T get() = _value ?: throwNotInjected(name)

    open fun set(value: T) {
        _value = value
    }
}