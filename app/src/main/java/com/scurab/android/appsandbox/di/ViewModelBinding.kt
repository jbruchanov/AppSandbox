package com.scurab.android.appsandbox.di

import androidx.lifecycle.ViewModel
import com.scurab.android.appsandbox.MainActivityViewModel
import com.scurab.android.feature1.di.Feature1ViewModelBinding
import com.scurab.appsandbox.core.android.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBinding : Feature1ViewModelBinding {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun postMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}