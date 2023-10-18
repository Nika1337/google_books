package com.example.googlebooks.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedBookApiModel(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: DetailedVolumeInfoApiModel,
    @SerialName("saleInfo")
    val saleInfo: SaleInfoApiModel,
    )

@Serializable
data class DetailedVolumeInfoApiModel(
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    val authors: List<String>? = null,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("mainCategory")
    val mainCategory: String? = null,
    @SerialName("categories")
    val categories: List<String> = listOf(),
    @SerialName("averageRating")
    val averageRating: Double? = null,
    @SerialName("ratingsCount")
    val ratingsCount: Int? = null,
    @SerialName("language")
    val language: String,
    @SerialName("imageLinks")
    val imageLinks: DetailedImageLinksApiModel? = null
)

@Serializable
data class DetailedImageLinksApiModel(
    @SerialName("smallThumbnail")
    val smallThumbnail: String,
    @SerialName("thumbnail")
    private val thumbnail: String
) {
    val httpsThumbnail : String
        get() = thumbnail.replace("http", "https")
}

@Serializable
data class SaleInfoApiModel(
    @SerialName("country")
    val country: String,
    @SerialName("saleability")
    val saleability: String,
    @SerialName("isEbook")
    val isEbook: Boolean
)