package com.example.googlebooks

import com.example.googlebooks.data.NetworkGoogleBooksRepository
import com.example.googlebooks.fake.FakeDataSource
import com.example.googlebooks.fake.FakeGoogleBooksApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkGoogleBooksRepositoryTest {
    @Test
    fun networkGoogleBooksRepository_getBooks_correctSearchResult() =
        runTest {
            val repository = NetworkGoogleBooksRepository(
                googleBooksApiService = FakeGoogleBooksApiService()
            )
            assertEquals(FakeDataSource.searchResultApiModel, repository.getBooks(""))
        }

    @Test
    fun networkGoogleBooksRepository_getBookUnknownId_nullReturned() =
        runTest {
            val repository = NetworkGoogleBooksRepository(
                googleBooksApiService = FakeGoogleBooksApiService()
            )

            assertEquals(null, repository.getBook("hello"))
        }

    @Test
    fun networkGoogleBooksRepository_getBookKnownId_correctBookReturned() =
        runTest {
            val repository = NetworkGoogleBooksRepository(
                googleBooksApiService = FakeGoogleBooksApiService()
            )
            val firstExpectedBook = FakeDataSource.convertBookApiModelToDetailedBookApiModel(
                FakeDataSource.bookApiModelList[0])
            val secondExpectedBook = FakeDataSource.convertBookApiModelToDetailedBookApiModel(
                FakeDataSource.bookApiModelList[1])
            val thirdExpectedBook = FakeDataSource.convertBookApiModelToDetailedBookApiModel(
                FakeDataSource.bookApiModelList[2])
            assertEquals(firstExpectedBook, repository.getBook(firstExpectedBook.id))
            assertEquals(secondExpectedBook, repository.getBook(secondExpectedBook.id))
            assertEquals(thirdExpectedBook, repository.getBook(thirdExpectedBook.id))
        }


}