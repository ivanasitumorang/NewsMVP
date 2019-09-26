package com.example.newsmvp.data.entities

data class Source(
    var id: String?,
    var name: String,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)