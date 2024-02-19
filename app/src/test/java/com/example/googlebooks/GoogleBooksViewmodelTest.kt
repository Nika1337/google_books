package com.example.googlebooks

import com.example.googlebooks.fake.FakeDataSource
import com.example.googlebooks.fake.FakeGetFormattedBooksUseCase
import com.example.googlebooks.fake.FakeGetFormattedDetailedBookUseCase
import com.example.googlebooks.rules.TestDispatcherRule
import com.example.googlebooks.ui.screens.GoogleBooksEvent
import com.example.googlebooks.ui.screens.GoogleBooksUiState
import com.example.googlebooks.ui.screens.GoogleBooksViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GoogleBooksViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun googleBooksViewModel_initializeViewModel_verifyGoogleBooksUIStateSuccess() =
        runTest {
            val googleBooksViewModel = GoogleBooksViewModel(
                getFormattedBooksUseCase = FakeGetFormattedBooksUseCase(),
                getFormattedDetailedBookUseCase = FakeGetFormattedDetailedBookUseCase()
            )
            assertEquals(
                GoogleBooksUiState.Success(
                    FakeDataSource.booksList
                ),
                googleBooksViewModel.googleBooksUiState

            )
        }


    @Test
    fun googleBooksViewModel_onEventQueryChanged_verifyQueryState() {
        val googleBooksViewModel = GoogleBooksViewModel(
            getFormattedBooksUseCase = FakeGetFormattedBooksUseCase(),
            getFormattedDetailedBookUseCase = FakeGetFormattedDetailedBookUseCase()
        )
        var expectedQuery = "hello dudes"
        googleBooksViewModel.onEvent(GoogleBooksEvent.QueryChanged(expectedQuery))

        assertEquals(
            expectedQuery,
            googleBooksViewModel.query
        )

        expectedQuery = ""
        googleBooksViewModel.onEvent(GoogleBooksEvent.QueryChanged(expectedQuery))

        assertEquals(
            expectedQuery,
            googleBooksViewModel.query
        )
    }

    @Test
    fun googleBooksViewModel_OnBookClicked_verifyCurrentChosenBook() {
        val googleBooksViewModel = GoogleBooksViewModel(
            getFormattedBooksUseCase = FakeGetFormattedBooksUseCase(),
            getFormattedDetailedBookUseCase = FakeGetFormattedDetailedBookUseCase()
        )
        googleBooksViewModel.onEvent(GoogleBooksEvent.BookClicked(""))
        assertEquals(FakeDataSource.detailedBook, googleBooksViewModel.currentChosenBook)
    }


}














