package com.scurab.android.feature1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.scurab.appsandbox.core.android.BaseViewModel
import com.scurab.appsandbox.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListFragmentViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) : BaseViewModel() {

    private val _persons = MutableLiveData<List<Person>>()
    val persons: LiveData<List<Person>> = _persons

    fun test() {
        logger.d("ListFragmentViewModel") { this.toString() }
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                val loadData = loadDataUseCase.loadData()
                loadData
            }
            _persons.postValue(data)
        }
    }
}