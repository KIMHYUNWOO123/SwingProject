package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class Collection(
    val id: Int,
    val title: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("last_collected_at")
    val lastCollectedAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("cover_photo")
    val coverPhoto: String,
    val user: String,
)
