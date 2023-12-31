package com.example.googlebooks.data

import com.example.googlebooks.model.api.DetailedBookApiModel
import com.example.googlebooks.model.api.SearchResultApiModel
import com.example.googlebooks.network.GoogleBooksApiService

interface GoogleBooksRepository {
    suspend fun getBooks(query: String): SearchResultApiModel
    suspend fun getBook(id: String): DetailedBookApiModel?
}

class NetworkGoogleBooksRepository(
    private val googleBooksApiService: GoogleBooksApiService
) : GoogleBooksRepository {
    override suspend fun getBooks(query: String)  = googleBooksApiService.getBooks(query = query)
    override suspend fun getBook(id: String) = googleBooksApiService.getBook(id = id)
}