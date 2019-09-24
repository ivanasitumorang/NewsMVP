package com.example.newsmvp.data.entities.newsapi

import com.example.newsmvp.data.entities.Source
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourcesResult(
    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("sources")
    @Expose
    val sources: List<Source>
)