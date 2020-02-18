package com.scurab.android.network

import com.scurab.appsandbox.model.Person
import javax.inject.Inject

interface IPersonRepository {
    suspend fun getList(): List<Person>
}

class NetworkPersonRepository @Inject constructor(private val webApi: RetrofitWebApi) :
    IPersonRepository by webApi