package com.scurab.appsandbox.core.android.coroutine

import androidx.lifecycle.LiveData

interface IProgressBarObservableAction {
    val progressBar: LiveData<Boolean>
    val progressBarVisibleAction: BooleanScopeAction

    class Impl : IProgressBarObservableAction {
        private val _progressBar = BooleanScopeAction()
        override val progressBar: LiveData<Boolean> = _progressBar
        override val progressBarVisibleAction = _progressBar
    }
}