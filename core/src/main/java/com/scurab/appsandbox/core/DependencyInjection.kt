package com.scurab.appsandbox.core

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class SessionScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class BaseActivityScope

interface HasAppComponent {
    val appComponent: DIComponent
}

interface DIComponentProvider<T : DIComponent>
interface DIComponent
