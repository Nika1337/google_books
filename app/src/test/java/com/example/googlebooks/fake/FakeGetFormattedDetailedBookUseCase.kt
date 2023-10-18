package com.example.googlebooks.fake

import com.example.googlebooks.domain.GetFormattedDetailedBookUseCase
import com.example.googlebooks.model.ui.DetailedBook

class FakeGetFormattedDetailedBookUseCase : GetFormattedDetailedBookUseCase(googleBooksRepository = FakeNetworkGoogleBooksRepository()) {
    override suspend operator fun invoke(id: String)  : DetailedBook? {
        return FakeDataSource.detailedBook
    }


}