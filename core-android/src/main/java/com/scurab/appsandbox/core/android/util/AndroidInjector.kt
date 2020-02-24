package com.scurab.appsandbox.core.android.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.scurab.appsandbox.core.di.DIComponent
import com.scurab.appsandbox.core.di.DIComponentProvider
import com.scurab.appsandbox.core.di.HasAppComponent

object AndroidInjector {

    @Suppress("UNCHECKED_CAST")
    fun <T : DIComponentProvider<U>, U : DIComponent> provider(
        context: Context,
        klass: Class<T>
    ): T {
        val appComponent = (context.applicationContext as? HasAppComponent)
            ?.appComponent

        return appComponent as T
    }

    fun <T : DIComponentProvider<U>, U : DIComponent> Fragment.provider(klass: Class<T>) =
        provider(requireContext(), klass)
}