package com.example.googlebooks.fake

import com.example.googlebooks.model.Book
import com.example.googlebooks.model.SearchResult
import com.example.googlebooks.network.GoogleBooksApiService

class FakeGoogleBooksApiService : GoogleBooksApiService {
    override suspend fun getBooks(query: String): SearchResult {
        return FakeDataSource.searchResult
    }

    override suspend fun getBook(id: String): Book? {
        return FakeDataSource.booksList.find { it.id == id }
    }

}