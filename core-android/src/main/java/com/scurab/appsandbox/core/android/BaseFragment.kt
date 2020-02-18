package com.scurab.appsandbox.core.android

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModelProvider by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewModelProvider(
            viewModelStore,
            viewModelFactory
        )
    }

    protected fun <T : ViewModel> viewModel(klass: Class<T>): T {
        return viewModelProvider.get(klass)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    protected abstract fun inject()
}