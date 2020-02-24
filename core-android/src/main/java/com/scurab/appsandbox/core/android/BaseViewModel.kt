package com.scurab.appsandbox.core.android

import androidx.lifecycle.ViewModel
import com.scurab.appsandbox.core.android.coroutine.CoroutineScopeAction
import com.scurab.appsandbox.core.android.coroutine.IProgressBarObservableAction
import com.scurab.appsandbox.core.android.di.IViewModelInjectableDispatchers
import com.scurab.appsandbox.core.collection.forEachReversed
import com.scurab.appsandbox.core.di.IInjectableLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel(),
    IInjectableLogger by IInjectableLogger.Impl(),
    IViewModelInjectableDispatchers by IViewModelInjectableDispatchers.Impl(),
    IProgressBarObservableAction by IProgressBarObservableAction.Impl() {

    protected fun CoroutineScope.launchWithActions(
        vararg actions: CoroutineScopeAction,
        block: suspend CoroutineScope.() -> Unit
    ) {
        actions.forEach { it.preExec() }
        launch {
            try {
                block()
            } finally {
                actions.forEachReversed { it.postExec(!this.isActive) }
            }
        }
    }

    override fun onCleared() {
        progressBarVisibleAction.clear()
        dispatchers.resumedHandler.clearQueue()
        super.onCleared()
    }
}