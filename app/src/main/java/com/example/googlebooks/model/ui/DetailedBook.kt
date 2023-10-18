package com.example.googlebooks.model.ui

data class DetailedBook(
    val id: String,
    val title: String,
    val author: String?,
    val description: String?,
    val pageCount: Int?,
    val mainCategory: String?,
    val averageRating: Double?,
    val thumbnailSrc: String
)