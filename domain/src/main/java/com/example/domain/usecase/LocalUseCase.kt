package com.example.domain.usecase

import com.example.domain.Repository
import com.example.domain.entity.PhotoData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUseCase @Inject constructor(
    private val repository: Repository,
) {
     fun getAllFlow(): Flow<List<PhotoData>> {
        return repository.getAllFlow()
    }

    suspend fun insertPhoto(photoData: PhotoData) {
        repository.insertPhoto(photoData)
    }

    suspend fun deletePhoto(id: String) {
        repository.deletePhoto(id)
    }
}