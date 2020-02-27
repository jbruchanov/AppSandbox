package com.scurab.appsandbox.core.android.util

interface ITaggable {
    val tags: MutableMap<Int, Any>

    class Impl : ITaggable {
        override val tags: MutableMap<Int, Any> = mutableMapOf()
    }
}