package com.scurab.appsandbox.core.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scurab.appsandbox.core.android.di.HasBaseActivityComponent
import com.scurab.appsandbox.core.android.di.IInjectableSavedStateViewModelFactoryProvider
import com.scurab.appsandbox.core.di.IInjectableLogger

abstract class BaseActivity : AppCompatActivity(),
    IInjectableLogger by IInjectableLogger.Impl(),
    IInjectableSavedStateViewModelFactoryProvider by IInjectableSavedStateViewModelFactoryProvider.Impl() {

    open val activityComponent by lazy {
        (application as HasBaseActivityComponent)
            .activityComponent(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    abstract fun inject()
}