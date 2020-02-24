package com.scurab.appsandbox.core.android.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.scurab.appsandbox.core.android.BaseActivity
import com.scurab.appsandbox.core.android.di.HasBaseActivityComponent
import com.scurab.appsandbox.core.di.DIComponent
import com.scurab.appsandbox.core.di.DIComponentProvider

object AndroidInjector {

    @Suppress("UNCHECKED_CAST")
    fun <T : DIComponentProvider<U>, U : DIComponent> provider(
        context: Context,
        klass: Class<T>
    ): T {
        val baseActivity = context.requireActivity(BaseActivity::class.java)
        //TODO: providers for other scopes ignored
        return baseActivity.activityComponent as? T
            ?: throw IllegalStateException("Invalid dagger setup")
    }

    fun <T : DIComponentProvider<U>, U : DIComponent> Fragment.provider(klass: Class<T>) =
        provider(requireContext(), klass)
}