package com.example.newsmvp.data.entities

data class Source(
    var id: String? = null,
    var name: String,
    val description: String? = null,
    val url: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null
)