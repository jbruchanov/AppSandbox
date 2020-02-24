package com.scurab.appsandbox.core.couroutines

import kotlinx.coroutines.MainCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface IDispatchers {
    val main: MainCoroutineDispatcher
    val io: CoroutineContext
    val cpu: CoroutineContext
}
