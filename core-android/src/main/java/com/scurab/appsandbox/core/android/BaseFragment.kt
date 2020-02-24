package com.scurab.appsandbox.core.android

import android.content.Context
import androidx.fragment.app.Fragment
import com.scurab.appsandbox.core.android.di.IInjectableSavedStateViewModelFactoryProvider
import com.scurab.appsandbox.core.di.IInjectableLogger

abstract class BaseFragment : Fragment(),
    IInjectableLogger by IInjectableLogger.Impl(),
    IInjectableSavedStateViewModelFactoryProvider by IInjectableSavedStateViewModelFactoryProvider.Impl() {

    protected open val viewModel: BaseViewModel? = null

    override fun onResume() {
        super.onResume()
        viewModel?.setResumed(true)
    }

    override fun onPause() {
        viewModel?.setResumed(false)
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    protected abstract fun inject()
}