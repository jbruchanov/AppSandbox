package com.scurab.appsandbox.core.android.coroutine

import com.scurab.appsandbox.core.couroutines.IDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class AndroidDispatchers : IDispatchers {
    override val main: MainCoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val cpu: CoroutineContext = Dispatchers.Default
}

