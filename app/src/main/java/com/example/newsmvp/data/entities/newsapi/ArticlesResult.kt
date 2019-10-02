package com.example.newsmvp.data.entities.newsapi

import com.example.newsmvp.data.entities.Article
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesResult(
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>
)