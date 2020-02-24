package com.scurab.android.appsandbox.di

import com.scurab.appsandbox.core.di.DIComponent
import com.scurab.appsandbox.core.di.SessionScope
import dagger.Subcomponent

@SessionScope
@Subcomponent
interface SessionComponent : DIComponent {

    fun activityComponent(module: ActivityModule) : BaseActivityComponent
}