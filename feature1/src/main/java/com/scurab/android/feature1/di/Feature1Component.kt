package com.scurab.android.feature1.di

import androidx.lifecycle.ViewModel
import com.scurab.android.feature1.ListFragment
import com.scurab.android.feature1.ListFragmentViewModel
import com.scurab.appsandbox.core.di.DIComponent
import com.scurab.appsandbox.core.di.DIComponentProvider
import com.scurab.appsandbox.core.android.di.ViewModelKey
import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap

interface Feature1ComponentProvider :
    DIComponentProvider<Feature1Component> {
    val feature1Component: Feature1Component
}

@Subcomponent
interface Feature1Component : DIComponent {
    fun inject(fragment: ListFragment)
}

interface Feature1ViewModelBinding {

    @Binds
    @IntoMap
    @ViewModelKey(ListFragmentViewModel::class)
    fun postListViewModel(viewModel: ListFragmentViewModel): ViewModel
}