package com.example.data

import com.example.domain.entity.PhotoData
import com.example.domain.entity.PhotoEntity

class Mapper {
    fun map(items: List<PhotoEntity>): List<PhotoData> {
        val list = mutableListOf<PhotoData>()
        items.forEach { item ->
            list.add(
                PhotoData(
                    id = item.id, url = item.urls.thumb
                )
            )
        }
        return list
    }
}