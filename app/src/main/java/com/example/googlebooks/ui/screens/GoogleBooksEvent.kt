package com.example.googlebooks.ui.screens

sealed interface GoogleBooksEvent {
    data class BookClicked(val id: String) : GoogleBooksEvent
    data class QueryChanged(val query: String) : GoogleBooksEvent
    object DetailScreenOnNavigateUp : GoogleBooksEvent
    object Search : GoogleBooksEvent
}