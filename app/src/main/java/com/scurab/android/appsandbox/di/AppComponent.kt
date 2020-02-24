package com.scurab.android.appsandbox.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.scurab.android.appsandbox.App
import com.scurab.android.appsandbox.MainActivity
import com.scurab.android.feature1.di.Feature1ComponentProvider
import com.scurab.appsandbox.core.Logger
import com.scurab.appsandbox.core.android.coroutine.AndroidDispatchers
import com.scurab.appsandbox.core.android.util.AndroidLogger
import com.scurab.appsandbox.core.couroutines.IDispatchers
import com.scurab.appsandbox.core.di.AppScope
import com.scurab.appsandbox.core.di.DIComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@AppScope
@Component(
    modules = [
        AppModule::class,
        AppViewModelBinding::class,
        NetworkModule::class
    ]
)
interface AppComponent : DIComponent,
    Feature1ComponentProvider {

    fun inject(app: App)
    fun inject(app: MainActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        //custom module
        fun appModule(appModule: AppModule): Builder

        //bind instance directly in the builder
        @BindsInstance
        fun app(app: App): Builder
    }
}

@Module
class AppModule(private val app: App) {
    @Provides
    @AppScope
    fun provideApp(): Application = app

    @Provides
    @AppScope
    fun provideLogger(): Logger = AndroidLogger()

    @Provides
    @AppScope
    fun provideViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    @AppScope
    fun provideDispatchers(): IDispatchers = AndroidDispatchers()
}

