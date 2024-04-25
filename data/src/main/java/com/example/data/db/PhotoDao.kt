package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.entity.PhotoData
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photoData")
    fun getAllFlow(): Flow<List<PhotoData>>

    @Insert
    fun insertPhoto(photoData: PhotoData)

    @Query("DELETE FROM photoData WHERE id IN (:id)")
    fun deletePhoto(id: String)
}