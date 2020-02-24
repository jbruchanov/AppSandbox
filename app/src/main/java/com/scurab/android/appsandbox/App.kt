package com.scurab.android.appsandbox

import android.app.Application
import com.scurab.android.appsandbox.di.AppComponent
import com.scurab.android.appsandbox.di.AppModule
import com.scurab.android.appsandbox.di.DaggerAppComponent
import com.scurab.appsandbox.core.di.HasAppComponent

class App : Application(), HasAppComponent {

    override fun onCreate() {
        super.onCreate()
    }

    private var _appComponent: AppComponent? = null
    override val appComponent: AppComponent
        get() {
            return _appComponent ?: DaggerAppComponent
                .builder()
                .app(this)
                .appModule(AppModule(this))
                .build()
                .also {
                    _appComponent = it
                }
        }
}