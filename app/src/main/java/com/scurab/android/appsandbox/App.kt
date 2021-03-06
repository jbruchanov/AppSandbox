package com.scurab.android.appsandbox

import android.app.Application
import com.scurab.android.appsandbox.di.*
import com.scurab.appsandbox.core.android.BaseActivity
import com.scurab.appsandbox.core.android.di.HasBaseSessionComponent
import com.scurab.appsandbox.core.di.HasAppComponent

class App : Application(), HasAppComponent, HasBaseSessionComponent {

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

    private var _sessionComponent: SessionComponent? = null
    override val sessionComponent: SessionComponent
        get() {
            return _sessionComponent ?: appComponent.sessionComponent().also {
                _sessionComponent = it
            }
        }
}