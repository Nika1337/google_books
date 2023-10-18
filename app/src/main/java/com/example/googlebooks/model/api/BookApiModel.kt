package com.example.googlebooks.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfoApiModel,
) {
    val httpsThumbnail: String
        get() = volumeInfo.imageLinksApiModel?.thumbnail?.replace("http", "https") ?: ""
}

@Serializable
data class VolumeInfoApiModel(
    @SerialName("title")
    val title: String = "N/A",
    @SerialName("imageLinks")
    val imageLinksApiModel: ImageLinksApiModel?
)

@Serializable
data class ImageLinksApiModel(
    @SerialName("thumbnail")
    val thumbnail: String = ""
) {
}
