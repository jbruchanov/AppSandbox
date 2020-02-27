package com.scurab.appsandbox.core.android.lifecycle

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.scurab.appsandbox.core.android.BaseViewModel
import com.scurab.appsandbox.core.android.ISavingState
import com.scurab.appsandbox.core.android.R
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
    fun createFactory(
        lifecycleOwner: LifecycleOwner,
        owner: SavedStateRegistryOwner
    ): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory(owner, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                val t = viewModels[modelClass]?.get() as T?
                    ?: npe("Unable to provide viewmodel of ${modelClass.name}")
                (t as? ISavingState)?.savedStateHandle = handle
                //attach observer
                (t as? BaseViewModel)
                    ?.takeIf { !it.tags.containsKey(R.id.tag_lifecycle_observer) }
                    ?.let {
                        lifecycleOwner.lifecycle.addObserver(ViewModelLifecycleObserver(it))
                    }
                return t
            }
        }
    }

    internal class ViewModelLifecycleObserver(private val viewModel: BaseViewModel) :
        LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            viewModel.apply {
                logger.d("ViewModel") { "Resumed $this" }
                dispatchers.resumedHandler.setResumed(true)
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            viewModel.apply {
                logger.d("ViewModel") { "Paused $this" }
                dispatchers.resumedHandler.setResumed(false)
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            viewModel.tags.remove(R.id.tag_lifecycle_observer)
        }
    }
}