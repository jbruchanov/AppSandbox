package com.scurab.android.appsandbox.di

import android.app.Application
import com.scurab.android.appsandbox.App
import com.scurab.appsandbox.core.AppScope
import com.scurab.appsandbox.core.DIComponent
import dagger.*

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent : DIComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        //custom module
        fun appModule(appModule: AppModule) : Builder
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
}