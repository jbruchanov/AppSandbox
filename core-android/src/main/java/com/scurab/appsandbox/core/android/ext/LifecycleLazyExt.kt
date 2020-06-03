package com.scurab.appsandbox.core.android.ext

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment

/**
 * Lazy tied to view's lifecycle
 */
fun <T> Fragment.viewBinding(initializer: () -> T) =
    com.scurab.appsandbox.core.lifecycleLazy({ viewLifecycleOwner.lifecycle }, initializer)


/**
 * Lazy tied to fragment's lifecycle
 */
fun <T> Fragment.lifecycleFragmentLazy(initializer: () -> T) =
    com.scurab.appsandbox.core.lifecycleLazy({ lifecycle }, initializer)

/**
 * Lazy tied to activities's lifecycle
 */
fun <T> ComponentActivity.viewBinding(initializer: () -> T) =
    com.scurab.appsandbox.core.lifecycleLazy({ lifecycle }, initializer)
