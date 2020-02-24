package com.scurab.appsandbox.core.di

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

class NotInjectedException(msg: String) : IllegalStateException(msg)

fun throwNotInjected(name: String): Nothing =
    throw NotInjectedException("$name not injected, use DI or call set($name) manually")