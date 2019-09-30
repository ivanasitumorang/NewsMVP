package com.example.newsmvp.data.network


import com.example.newsmvp.data.entities.newsapi.ArticlesResult
import com.example.newsmvp.data.entities.newsapi.SourcesResult
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/"

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NewsApiService {
    @GET("v2/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("language") language: String,
        @Query("country") country: String
    ) : Flowable<SourcesResult>

    @GET("v2/everything")
    fun getArticlesBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") source: String
    ) : Flowable<ArticlesResult>
}

object NewsApi {
    const val API_KEY = "12a73ae6bd25447e96fbe42b5a320ffc"
    val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}