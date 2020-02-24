package com.scurab.appsandbox.core.android.view

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.scurab.appsandbox.core.android.BaseViewModel

interface HasProgressBar {
    val progressBar: View

    fun bind(viewModel: BaseViewModel, fragment: Fragment) : ProgressBarMultiHandler {
        return bind(viewModel, fragment as LifecycleOwner)
    }

    fun bind(viewModel: BaseViewModel, lifecycleOwner: LifecycleOwner): ProgressBarMultiHandler {
        return ProgressBarMultiHandler().bind(progressBar, viewModel, lifecycleOwner)
    }
}