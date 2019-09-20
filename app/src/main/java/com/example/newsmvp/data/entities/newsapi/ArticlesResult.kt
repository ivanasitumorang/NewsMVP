package com.example.newsmvp.data.entities.newsapi

import androidx.annotation.Keep
import com.example.newsmvp.data.entities.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class ArticlesResult(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int,

    @SerializedName("articles")
    @Expose
    val articles: List<Article>
)