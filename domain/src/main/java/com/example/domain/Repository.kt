package com.example.domain

import androidx.paging.PagingData
import com.example.domain.entity.PhotoData
import kotlinx.coroutines.flow.Flow

interface Repository {

    // REMOTE
    suspend fun getPhotos(query: String, key: String): Flow<PagingData<PhotoData>>

    // LOCAL
    fun getAllFlow(): Flow<List<PhotoData>>

    suspend fun insertPhoto(photoData: PhotoData)

    suspend fun deletePhoto(id: String)
}