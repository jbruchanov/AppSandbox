package com.scurab.appsandbox.core.android

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scurab.appsandbox.core.di.IInjectableLogger
import javax.inject.Inject

abstract class BaseFragment : Fragment(),
    IInjectableLogger by IInjectableLogger.Impl() {

    protected open val viewModel: BaseViewModel? = null

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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