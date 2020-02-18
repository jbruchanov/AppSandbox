package com.scurab.android.appsandbox

import android.os.Bundle
import androidx.activity.viewModels
import com.scurab.appsandbox.core.android.BaseActivity
import com.scurab.appsandbox.core.android.BaseViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

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