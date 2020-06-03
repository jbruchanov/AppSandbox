package com.scurab.android.feature1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.scurab.appsandbox.core.android.BaseViewModel
import com.scurab.appsandbox.core.android.util.savedState
import com.scurab.appsandbox.model.Person
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.reflect.KProperty

class ListFragmentViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) : BaseViewModel() {

    private val _persons = MutableLiveData<List<Person>>()
    val persons: LiveData<List<Person>> = _persons

    fun test() {
        logger.d("ListFragmentViewModel") { this.toString() }
        viewModelScope.launchWithActions(progressBarVisibleAction) {
            try {
                val data = withContext(dispatchers.io) {
                    val loadData = loadDataUseCase.loadData()
                    loadData
                }
                _persons.postValue(data)
            } catch (e: Exception) {
                logger.e("ListFragmentViewModel", e) { "Data Loading" }
            }

            withContext(viewModelScope.coroutineContext) {
                //navigation here
            }
        }
    }
}