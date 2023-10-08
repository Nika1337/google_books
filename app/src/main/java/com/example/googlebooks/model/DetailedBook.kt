package com.example.googlebooks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedBook(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    val authors: List<String>,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("description")
    val description: String,
    @SerialName("language")
    val language: String,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val thumbnail: String
)
