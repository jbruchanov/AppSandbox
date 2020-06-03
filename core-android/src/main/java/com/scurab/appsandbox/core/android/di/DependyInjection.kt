package com.scurab.appsandbox.core.android.di

import androidx.lifecycle.ViewModel
import com.scurab.appsandbox.core.android.BaseActivity
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
//dagger
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

interface IBaseSessionComponent {
    fun inject(baseActivity: BaseActivity)
}

interface HasBaseSessionComponent {
    val sessionComponent: IBaseSessionComponent
}