package com.scurab.appsandbox.core.android.coroutine

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 * Basic boolean action used in viewmodels when new coroutine launched
 */
class BooleanScopeAction(
    private val preExec: Boolean = true,
    private val postExec: Boolean = false,
    private val onCancel: Boolean = postExec
) : LiveData<Boolean>(false), CoroutineScopeAction {

    override fun preExec() = postValue(preExec)

    override fun postExec(isCancelling: Boolean) =
        postValue(if (isCancelling) onCancel else postExec)

    @MainThread
    fun clear() {
        value = onCancel
    }
}