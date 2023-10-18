package com.example.googlebooks.fake

import com.example.googlebooks.domain.GetFormattedBooksUseCase

class FakeGetFormattedBooksUseCase : GetFormattedBooksUseCase(googleBooksRepository = FakeNetworkGoogleBooksRepository()) {
    override suspend operator fun invoke(query: String) = FakeDataSource.booksList

}