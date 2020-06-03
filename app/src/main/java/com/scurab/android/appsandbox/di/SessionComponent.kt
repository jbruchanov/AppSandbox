package com.scurab.android.appsandbox.di

import com.scurab.android.appsandbox.MainActivity
import com.scurab.android.feature1.di.Feature1ComponentProvider
import com.scurab.appsandbox.core.android.di.IBaseSessionComponent
import com.scurab.appsandbox.core.di.SessionScope
import dagger.Subcomponent

@SessionScope
@Subcomponent
interface SessionComponent : IBaseSessionComponent,
    Feature1ComponentProvider {

    fun inject(app: MainActivity)
}