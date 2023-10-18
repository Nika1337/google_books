package com.example.googlebooks.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultApiModel(
    @SerialName("items")
    val books: List<BookApiModel> = listOf()
)

