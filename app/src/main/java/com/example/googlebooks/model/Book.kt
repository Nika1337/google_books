package com.example.googlebooks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    private val volumeInfo: VolumeInfo,
) {
    val httpsThumbnail: String
        get() = volumeInfo.imageLinks.thumbnail.replace("http", "https")
}

@Serializable
data class VolumeInfo(
    @SerialName("imageLinks")
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val thumbnail: String
) {
}
