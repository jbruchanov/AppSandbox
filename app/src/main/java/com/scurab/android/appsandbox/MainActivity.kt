package com.scurab.android.appsandbox

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.scurab.android.appsandbox.databinding.MainActivityBinding
import com.scurab.appsandbox.core.android.BaseActivity
import com.scurab.appsandbox.core.android.BaseViewModel
import com.scurab.appsandbox.core.android.view.HasProgressBar
import com.scurab.appsandbox.core.android.lifecycle.viewBinding
import javax.inject.Inject

class MainActivity : BaseActivity(), HasProgressBar {

    private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }
    private val viewBinding by viewBinding { MainActivityBinding.bind(window.decorView) }
    override val progressBar: View get() = viewBinding.progressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel.toString()
    }

    override fun inject() {
        (application as App).appComponent
            .inject(this)
    }
}

class MainActivityViewModel @Inject constructor() : BaseViewModel() {

}