package com.example.googlebooks.fake

import com.example.googlebooks.data.NetworkGoogleBooksRepository
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
            assertEquals(FakeDataSource.searchResult, repository.getBooks(""))
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
            val firstExpectedBook = FakeDataSource.booksList[0]
            val secondExpectedBook = FakeDataSource.booksList[1]
            val thirdExpectedBook = FakeDataSource.booksList[2]
            assertEquals(firstExpectedBook, repository.getBook(firstExpectedBook.id))
            assertEquals(secondExpectedBook, repository.getBook(secondExpectedBook.id))
            assertEquals(thirdExpectedBook, repository.getBook(thirdExpectedBook.id))
        }


}