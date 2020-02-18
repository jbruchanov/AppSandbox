package com.scurab.appsandbox.core.android

import androidx.lifecycle.ViewModel
import com.scurab.appsandbox.core.Logger
import com.scurab.appsandbox.core.ref
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    private var _logger: Logger? = null
    val logger: Logger get() = _logger.ref

    @Inject
    fun setLogger(logger: Logger) {
        check(_logger == null)
        this._logger = logger
    }
}