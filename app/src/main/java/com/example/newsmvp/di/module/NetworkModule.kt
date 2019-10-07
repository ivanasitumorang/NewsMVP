package com.example.newsmvp.di.module

import android.content.Context
import com.example.newsmvp.BuildConfig
import com.example.newsmvp.data.network.NewsApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule (private val context: Context) {
    @Provides
    fun provideRestClientRetrofit() : Retrofit {
        val retrofit = Retrofit.Builder()
        val gson = GsonBuilder().setLenient().create()
        retrofit.baseUrl(BuildConfig.NEWSAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return retrofit.build()
    }

    @Provides
    fun provideNewsApiService(retrofit: Retrofit) : NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}