package com.scurab.appsandbox.core.android.util

import com.scurab.appsandbox.core.android.ISavingState
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> ISavingState.savedState(defaultValue: T) =
    SavedStateHandlerProperty(defaultValue) { this }

class SavedStateHandlerProperty<T>(
    private val defaultValue: T,
    private val promise: () -> ISavingState
) : ReadWriteProperty<Any?, T> {
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        promise().savedStateHandle.set(property.name, value)
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return promise().savedStateHandle.get<T>(property.name) ?: defaultValue
    }
}