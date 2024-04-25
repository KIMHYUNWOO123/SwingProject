package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.Repository
import com.example.domain.entity.PhotoData
import com.example.domain.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun getPhotos(query: String, key: String): Flow<PagingData<PhotoData>> {
        return repository.getPhotos(query, key)
    }
}