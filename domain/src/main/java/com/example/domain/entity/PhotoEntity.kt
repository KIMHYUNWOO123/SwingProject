package com.example.domain.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PhotoEntity(
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @SerializedName("blur_hash")
    val blurHash: String,
    val downloads: Int,
    val likes: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val description: String?,
    val exif: Exif?,
    val location: Location?,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Collection>,
    val urls: Urls,
    val links: Links,
    val user: User,
)

