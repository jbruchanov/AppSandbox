package com.scurab.android.appsandbox.di

import com.google.gson.Gson
import com.scurab.android.network.RetrofitWebApi
import com.scurab.appsandbox.core.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://www.scurab.com/sample/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @AppScope
    @Provides
    fun provideWebApi(retrofit: Retrofit): RetrofitWebApi {
        return retrofit.create(RetrofitWebApi::class.java)
    }
}