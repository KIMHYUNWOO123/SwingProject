package com.example.domain.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "photoData")
data class PhotoData(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val url: String,
)