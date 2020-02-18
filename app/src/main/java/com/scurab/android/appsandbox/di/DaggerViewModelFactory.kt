package com.scurab.android.appsandbox.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scurab.appsandbox.core.AppScope
import com.scurab.appsandbox.core.npe
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class DaggerViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T?
            ?: npe("Unable to provide viewmodel of ${modelClass.name}")
    }
}