package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.PhotoData

@Database(
    entities = [PhotoData::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}