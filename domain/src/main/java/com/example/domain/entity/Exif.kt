package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class Exif(
    val make: String,
    val model: String,
    @SerializedName("exposure_time")
    val exposureTime: String,
    val aperture: String,
    @SerializedName("focal_length")
    val focalLength: String,
    val iso: Int
)