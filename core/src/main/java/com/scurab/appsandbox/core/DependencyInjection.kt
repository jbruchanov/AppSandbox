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


interface DIComponentProvider<T : DIComponent> {
    val component: T
}

interface DIComponent