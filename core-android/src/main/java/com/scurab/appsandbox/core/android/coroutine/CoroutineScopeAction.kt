package com.scurab.appsandbox.core.android.coroutine

interface CoroutineScopeAction {
    fun preExec()
    fun postExec(isCancelling: Boolean)
}