package com.scurab.appsandbox.core.android

import com.scurab.appsandbox.core.Logger
import com.scurab.appsandbox.core.npe
import javax.inject.Inject

abstract class BaseUseCase {
    private var _logger: Logger? = null
    val logger: Logger
        get() = _logger ?: npe(
            "Logger is null, instance hasn't be provided by DI.\n" +
                    "Call #setLogger() manually first!"
        )

    @Inject
    fun setLogger(logger: Logger) {
        check(_logger == null)
        this._logger = logger
    }
}