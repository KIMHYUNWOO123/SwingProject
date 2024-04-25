package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.PhotoData

@Database(
    entities = [PhotoData::class],
    version = 2,
    exportSchema = false
)
abstract class PhotoDB : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}