package com.example.googlebooks.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    @SerialName("items")
    val books: List<DetailedBook>
)

