package com.example.googlebooks

import com.example.googlebooks.domain.GetFormattedBooksUseCase
import com.example.googlebooks.fake.FakeDataSource
import com.example.googlebooks.fake.FakeNetworkGoogleBooksRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetFormattedBooksUseCaseTest {
    @Test
    fun getFormattedBooksUseCase_invoke_correctFormattedBooksResult() =
        runTest {
            val getFormattedBooksUseCase = GetFormattedBooksUseCase(
                googleBooksRepository = FakeNetworkGoogleBooksRepository()
            )
            assertEquals(FakeDataSource.booksList, getFormattedBooksUseCase(""))
        }
}