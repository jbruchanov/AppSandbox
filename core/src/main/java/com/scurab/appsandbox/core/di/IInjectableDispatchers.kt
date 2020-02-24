package com.scurab.appsandbox.core.di

import com.scurab.appsandbox.core.couroutines.IDispatchers
import javax.inject.Inject

interface IInjectableDispatchers {
    val dispatchers: IDispatchers

    @Inject
    fun set(value: IDispatchers)

    class Impl : InjectableField<IDispatchers>("Dispatchers"), IInjectableDispatchers {
        override val dispatchers: IDispatchers get() = value
    }
}