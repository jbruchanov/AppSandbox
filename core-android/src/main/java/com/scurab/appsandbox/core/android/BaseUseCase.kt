package com.scurab.appsandbox.core.android

import com.scurab.appsandbox.core.di.IInjectableDispatchers
import com.scurab.appsandbox.core.di.IInjectableLogger

abstract class BaseUseCase :
    IInjectableLogger by IInjectableLogger.Impl(),
    IInjectableDispatchers by IInjectableDispatchers.Impl()