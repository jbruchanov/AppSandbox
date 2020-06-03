package com.scurab.appsandbox.core.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scurab.appsandbox.core.android.di.HasBaseSessionComponent
import com.scurab.appsandbox.core.android.di.IInjectableSavedStateViewModelFactoryProvider
import com.scurab.appsandbox.core.di.IInjectableLogger

abstract class BaseActivity : AppCompatActivity(),
    IInjectableLogger by IInjectableLogger.Impl(),
    IInjectableSavedStateViewModelFactoryProvider by IInjectableSavedStateViewModelFactoryProvider.Impl() {

    val sessionComponent get() = (application as HasBaseSessionComponent).sessionComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    abstract fun inject()
}