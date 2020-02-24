package com.scurab.appsandbox.core.android.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

/**
 * Unwrap [AppCompatActivity] from Context
 */
fun Context.requireActivity(): AppCompatActivity {
    return requireActivity(AppCompatActivity::class.java)
}

/**
 * Unwrap specific activity from Context
 */
fun <T : Activity> Context.requireActivity(klass: Class<out T>): T {
    var thiz: Context? = this
    while (thiz != null) {
        if (thiz::class.java == klass) {
            return this as T
        } else {
            thiz = (this as? ContextWrapper)?.baseContext
        }
    }
    throw IllegalStateException("Unable to get activity of type:${klass.name} from $this")
}