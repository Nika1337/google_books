package com.example.googlebooks.data

import com.example.googlebooks.model.Book
import com.example.googlebooks.model.SearchResult
import com.example.googlebooks.network.GoogleBooksApiService

interface GoogleBooksRepository {
    suspend fun getBooks(query: String): SearchResult
    suspend fun getBook(id: String): Book?
}

class NetworkGoogleBooksRepository(
    private val googleBooksApiService: GoogleBooksApiService
) : GoogleBooksRepository {
    override suspend fun getBooks(query: String)  = googleBooksApiService.getBooks(query = query)
    override suspend fun getBook(id: String) = googleBooksApiService.getBook(id = id)
}