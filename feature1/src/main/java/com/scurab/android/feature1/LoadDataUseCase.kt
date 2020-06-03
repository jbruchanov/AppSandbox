package com.scurab.android.feature1

import com.scurab.android.network.NetworkPersonRepository
import com.scurab.appsandbox.core.android.BaseUseCase
import com.scurab.appsandbox.core.npe
import com.scurab.appsandbox.model.Person
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: NetworkPersonRepository
) : BaseUseCase() {

    suspend fun loadData(): List<Person> {
        delay(3000)
        return repository.getList()
    }
}