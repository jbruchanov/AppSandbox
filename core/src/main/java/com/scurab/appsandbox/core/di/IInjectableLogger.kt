package com.scurab.appsandbox.core.di

import com.scurab.appsandbox.core.Logger
import javax.inject.Inject

interface IInjectableLogger {
    val logger: Logger
    @Inject
    fun set(value: Logger)

    class Impl : InjectableField<Logger>("Logger"), IInjectableLogger {
        override val logger: Logger get() = value
    }
}
