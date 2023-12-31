package com.example.googlebooks.fake

import com.example.googlebooks.model.api.DetailedBookApiModel
import com.example.googlebooks.model.api.SearchResultApiModel
import com.example.googlebooks.network.GoogleBooksApiService

class FakeGoogleBooksApiService : GoogleBooksApiService {
    override suspend fun getBooks(query: String): SearchResultApiModel {
        return FakeDataSource.searchResultApiModel
    }

    override suspend fun getBook(id: String): DetailedBookApiModel? {
        val bookApiModel = FakeDataSource.bookApiModelList.find { it.id == id } ?: return null
        return FakeDataSource.convertBookApiModelToDetailedBookApiModel(bookApiModel = bookApiModel)
    }

}