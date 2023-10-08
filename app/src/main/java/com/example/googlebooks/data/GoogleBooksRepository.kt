package com.example.googlebooks.data

import com.example.googlebooks.model.DetailedBook
import com.example.googlebooks.model.SearchResult
import com.example.googlebooks.network.GoogleBooksApiService

interface GoogleBooksRepository {
    suspend fun getBooks(query: String): SearchResult
    suspend fun getBook(id: String): DetailedBook
}

class NetworkGoogleBooksRepository(
    private val googleBooksApiService: GoogleBooksApiService
) : GoogleBooksRepository {
    override suspend fun getBooks(query: String): SearchResult = googleBooksApiService.getBooks(query = query)
    override suspend fun getBook(id: String): DetailedBook = googleBooksApiService.getBook(id = id)
}