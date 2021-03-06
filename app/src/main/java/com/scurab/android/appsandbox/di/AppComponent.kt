package com.scurab.android.appsandbox.di

import android.app.Application
import com.scurab.android.appsandbox.App
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
interface AppComponent : DIComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        //custom module
        fun appModule(appModule: AppModule): Builder

        //bind instance directly in the builder
        @BindsInstance
        fun app(app: App): Builder
    }

    fun sessionComponent() : SessionComponent
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
    fun provideDispatchers(): IDispatchers = AndroidDispatchers()
}

