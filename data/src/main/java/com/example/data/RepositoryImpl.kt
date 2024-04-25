package com.example.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.db.PhotoDB
import com.example.domain.Repository
import com.example.domain.entity.PhotoData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val db: PhotoDB,
) : Repository {
    override suspend fun getPhotos(query: String, key: String): Flow<PagingData<PhotoData>> {
        val pagingFactory = { PagingSource(apiService, key = key, query = query) }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingFactory
        ).flow
    }

    override fun getAllFlow(): Flow<List<PhotoData>> {
        return db.photoDao().getAllFlow()
    }

    override suspend fun insertPhoto(photoData: PhotoData) {
        db.photoDao().insertPhoto(photoData)
    }

    override suspend fun deletePhoto(id: String) {
        db.photoDao().deletePhoto(id)
    }

    companion object {
        const val PAGE_SIZE = 30
    }
}