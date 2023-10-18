package com.example.googlebooks.fake

import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.model.api.DetailedBookApiModel
import com.example.googlebooks.model.api.SearchResultApiModel

class FakeNetworkGoogleBooksRepository : GoogleBooksRepository{
    override suspend fun getBooks(query: String): SearchResultApiModel {
        return FakeDataSource.searchResultApiModel
    }

    override suspend fun getBook(id: String): DetailedBookApiModel? {
        return FakeDataSource.detailedBookApiModel
    }
}