package com.example.googlebooks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedBook(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: DetailedVolumeInfo,
    @SerialName("saleInfo")
    val saleInfo: SaleInfo,

)

@Serializable
data class DetailedVolumeInfo(
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
    val categories: List<String>,
    @SerialName("averageRating")
    val averageRating: Double? = null,
    @SerialName("ratingsCount")
    val ratingsCount: Int? = null,
    @SerialName("language")
    val language: String,
    @SerialName("imageLinks")
    val imageLinks: DetailedImageLinks
)

@Serializable
data class DetailedImageLinks(
    @SerialName("smallThumbnail")
    val smallThumbnail: String,
    @SerialName("thumbnail")
    val thumbnail: String
)

@Serializable
data class SaleInfo(
    @SerialName("country")
    val country: String,
    @SerialName("saleability")
    val saleability: String,
    @SerialName("isEbook")
    val isEbook: Boolean
)