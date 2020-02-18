package com.scurab.android.feature1

import com.scurab.android.network.NetworkPersonRepository
import com.scurab.appsandbox.core.android.BaseUseCase
import com.scurab.appsandbox.model.Person
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: NetworkPersonRepository) :
    BaseUseCase() {

    operator fun invoke() {

    }

    suspend fun loadData(): List<Person> {
        return repository.getList()
    }
}