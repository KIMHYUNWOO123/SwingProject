package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val username: String,
    val name: String,
    @SerializedName("portfolio_url")
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    @SerializedName("total_likes")
    val totalLikes: Int,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("total_collections")
    val totalCollections: Int,
    @SerializedName("instagram_username")
    val instagramUsername: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    val links: UserLinks,
)