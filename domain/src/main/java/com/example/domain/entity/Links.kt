package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class Links(
    val self: String,
    val html: String,
    val download: String,
    @SerializedName("download_location")
    val downloadLocation: String,
)
