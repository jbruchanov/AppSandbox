package com.scurab.android.feature1

import com.scurab.appsandbox.core.android.BaseUseCase
import javax.inject.Inject

class LoadDataUseCase @Inject constructor() : BaseUseCase() {

    operator fun invoke() {
        logger.d("LoadDataUseCase") { this.toString() }
    }
}