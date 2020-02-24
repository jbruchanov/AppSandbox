package com.scurab.appsandbox.core.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scurab.appsandbox.core.android.coroutine.ViewModelDispatchers
import com.scurab.appsandbox.core.couroutines.IDispatchers
import com.scurab.appsandbox.core.di.InjectableField
import javax.inject.Inject

interface IViewModelInjectableDispatchers {
    //as we need viewModelScope, only way is to have this with receiver
    val ViewModel.dispatchers: ViewModelDispatchers

    /**
     * Set state of viewModel's handler
     */
    fun setResumed(resumed: Boolean)

    @Inject
    fun set(value: IDispatchers)

    class Impl : IViewModelInjectableDispatchers, InjectableField<IDispatchers>("Dispatchers") {
        private var viewModelDispatchers: ViewModelDispatchers? = null

        override val ViewModel.dispatchers: ViewModelDispatchers
            get() = viewModelDispatchers ?: ViewModelDispatchers(viewModelScope, value)
                    .also { viewModelDispatchers = it }

        override fun setResumed(resumed: Boolean) {
            viewModelDispatchers?.resumedHandler?.setResumed(resumed)
        }
    }
}