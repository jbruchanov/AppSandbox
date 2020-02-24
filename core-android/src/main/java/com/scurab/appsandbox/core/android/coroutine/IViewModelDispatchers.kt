package com.scurab.appsandbox.core.android.coroutine

import android.os.Looper
import com.scurab.appsandbox.core.couroutines.IDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.android.asCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface IViewModelDispatchers : IDispatchers {
    val resumed: CoroutineContext
}

/**
 * ViewModel's dispatchers holder
 */
class ViewModelDispatchers(
    private val viewModelScope: CoroutineScope,
    dispatchers: IDispatchers
) : IViewModelDispatchers, IDispatchers by dispatchers {
    internal val resumedHandler = PausableHandler(Looper.getMainLooper())
    override val resumed: CoroutineContext
        get() = viewModelScope.coroutineContext + resumedHandler.asCoroutineDispatcher(
            "PausableHandler"
        )
}