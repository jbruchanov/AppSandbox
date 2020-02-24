package com.scurab.appsandbox.core.android.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.scurab.appsandbox.core.android.ISavingState
import com.scurab.appsandbox.core.di.AppScope
import com.scurab.appsandbox.core.npe
import javax.inject.Inject
import javax.inject.Provider

/**
 * Provider for SavedStateViewModelFactory
 * Because it has to have reference to activity/fragment it would need to create Activity/Fragment scope for dagger.
 * So let's keep it simple and just handle it via provider.
 */
@AppScope
class SavedStateViewModelFactoryProvider @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) {
    fun createFactory(owner: SavedStateRegistryOwner): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory(owner, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                val t = viewModels[modelClass]?.get() as T?
                    ?: npe("Unable to provide viewmodel of ${modelClass.name}")
                (t as ISavingState).savedStateHandle = handle
                return t
            }
        }
    }
}