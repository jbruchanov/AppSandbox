package com.scurab.android.appsandbox.di

import androidx.savedstate.SavedStateRegistryOwner
import com.scurab.android.appsandbox.MainActivity
import com.scurab.android.feature1.di.Feature1ComponentProvider
import com.scurab.appsandbox.core.android.BaseActivity
import com.scurab.appsandbox.core.android.di.IBaseActivityComponent
import com.scurab.appsandbox.core.di.BaseActivityScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@BaseActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface BaseActivityComponent : IBaseActivityComponent,
    Feature1ComponentProvider {

    fun inject(app: MainActivity)
}

@Module
class ActivityModule(private val activity: BaseActivity) {
    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideStoreOwner(): SavedStateRegistryOwner {
        return activity
    }
}



