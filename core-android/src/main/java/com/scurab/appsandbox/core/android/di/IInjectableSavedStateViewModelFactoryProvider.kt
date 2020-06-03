package com.scurab.appsandbox.core.android.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.scurab.appsandbox.core.android.lifecycle.SavedStateViewModelFactoryProvider
import com.scurab.appsandbox.core.di.InjectableField
import javax.inject.Inject

interface IInjectableSavedStateViewModelFactoryProvider {

    val Fragment.viewModelFactory: AbstractSavedStateViewModelFactory
    val AppCompatActivity.viewModelFactory: AbstractSavedStateViewModelFactory

    @Inject
    fun set(value: SavedStateViewModelFactoryProvider)

    class Impl : InjectableField<SavedStateViewModelFactoryProvider>(
        IInjectableSavedStateViewModelFactoryProvider::class.java.name
    ), IInjectableSavedStateViewModelFactoryProvider {

        private var _viewModelFactory: AbstractSavedStateViewModelFactory? = null

        override val Fragment.viewModelFactory
            get() = _viewModelFactory ?: value.createFactory(this, this).also {
                _viewModelFactory = it
            }

        override val AppCompatActivity.viewModelFactory
            get() = _viewModelFactory ?: value.createFactory(this, this).also {
                _viewModelFactory = it
            }
    }
}