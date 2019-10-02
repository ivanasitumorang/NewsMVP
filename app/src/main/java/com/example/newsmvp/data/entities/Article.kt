package com.example.newsmvp.data.entities

data class Article(
    var source: Source,
    var author: String? = null,
    var title: String? = null,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
)