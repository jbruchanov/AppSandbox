package com.scurab.appsandbox.core.android.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.scurab.appsandbox.core.di.InjectableField
import javax.inject.Inject

interface IInjectableSavedStateViewModelFactoryProvider {

    val SavedStateRegistryOwner.viewModelFactory: AbstractSavedStateViewModelFactory

    @Inject
    fun set(value: SavedStateViewModelFactoryProvider)

    class Impl : InjectableField<SavedStateViewModelFactoryProvider>(
        IInjectableSavedStateViewModelFactoryProvider::class.java.name
    ), IInjectableSavedStateViewModelFactoryProvider {

        private var _viewModelFactory: AbstractSavedStateViewModelFactory? = null

        override val SavedStateRegistryOwner.viewModelFactory
            get() = _viewModelFactory ?: value.createFactory(this).also { _viewModelFactory = it }
    }
}