package com.scurab.appsandbox.core.android.util

import android.util.Log
import com.scurab.appsandbox.core.Logger
import com.scurab.appsandbox.core.android.BuildConfig

class AndroidLogger : Logger() {

    override val isEnabled: Boolean =
        BuildConfig.DEBUG
    override val error: (String, String) -> Unit = { tag, msg -> Log.e(tag, msg) }
    override val debug: (String, String) -> Unit = { tag, msg -> Log.d(tag, msg) }
}