package com.example.googlebooks

import com.example.googlebooks.domain.GetFormattedDetailedBookUseCase
import com.example.googlebooks.fake.FakeDataSource
import com.example.googlebooks.fake.FakeNetworkGoogleBooksRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetFormattedDetailedBookUseCaseTest {
    @Test
    fun getFormattedDetailedBookUseCase_invoke_correctFormattedDetailedBookResult() =
        runTest {
            val getFormattedDetailedBookUseCase = GetFormattedDetailedBookUseCase(
                googleBooksRepository = FakeNetworkGoogleBooksRepository()
            )
            assertEquals(FakeDataSource.detailedBook, getFormattedDetailedBookUseCase(""))
        }

}