package com.scurab.android.network

import com.scurab.appsandbox.model.Person
import retrofit2.http.GET

interface RetrofitWebApi : IPersonRepository {

    @GET("list.json")
    override suspend fun getList(): List<Person>
}