package com.scurab.android.appsandbox

import android.app.Application
import com.scurab.android.appsandbox.di.AppComponent
import com.scurab.android.appsandbox.di.AppModule
import com.scurab.android.appsandbox.di.DaggerAppComponent
import com.scurab.appsandbox.core.DIComponentProvider

class App : Application(), DIComponentProvider<AppComponent> {

    override fun onCreate() {
        super.onCreate()
    }

    private var _component: AppComponent? = null
    override val component: AppComponent
        get() {
            return _component ?: DaggerAppComponent
                .builder()
                .app(this)
                .appModule(AppModule(this))
                .build()
                .also {
                    _component = it
                }
        }
}