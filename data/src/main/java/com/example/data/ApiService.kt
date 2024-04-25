package com.example.data

import com.example.domain.entity.PhotoEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos/random")
    suspend fun searchPhoto(
        @Query("query") query: String,
        @Query("count") count: Int,
        @Query("client_id") key: String,
    ): List<PhotoEntity>
}