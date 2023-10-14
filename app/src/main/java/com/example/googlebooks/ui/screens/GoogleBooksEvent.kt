package com.example.googlebooks.ui.screens

sealed interface GoogleBooksEvent {
    data class BookClicked(val id: String) : GoogleBooksEvent

    data class Search(val query: String) : GoogleBooksEvent
}