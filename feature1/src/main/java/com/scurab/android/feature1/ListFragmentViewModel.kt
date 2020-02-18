package com.scurab.android.feature1

import com.scurab.appsandbox.core.android.BaseViewModel
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) : BaseViewModel() {
    fun test() {
        logger.d("ListFragmentViewModel") { this.toString() }
        loadDataUseCase()
    }
}