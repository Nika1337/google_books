package com.example.googlebooks.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlebooks.ui.screens.GoogleBooksEvent
import com.example.googlebooks.ui.screens.GoogleBooksViewModel
import com.example.googlebooks.ui.screens.books.GoogleBooksHomeScreen
import com.example.googlebooks.ui.screens.details.BookDetailsScreen
import com.example.googlebooks.ui.utils.BooksContentType
import com.example.googlebooks.ui.utils.BooksCurrentScreen

@Composable
fun GoogleBooksApp(windowSize: WindowWidthSizeClass) {
    val viewModel: GoogleBooksViewModel = viewModel(factory = GoogleBooksViewModel.Factory)
    val booksContentType = when(windowSize) {
        WindowWidthSizeClass.Compact -> {
            BooksContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            BooksContentType.LIST_AND_DETAIL
        }
        WindowWidthSizeClass.Expanded -> {
            BooksContentType.LIST_AND_DETAIL
        }
        else -> {
            BooksContentType.LIST_ONLY
        }
    }

    if (booksContentType == BooksContentType.LIST_ONLY) {
        if (viewModel.currentScreen == BooksCurrentScreen.LIST) {
            GoogleBooksHomeScreen(
                onSearchClicked = {
                    viewModel.onEvent(GoogleBooksEvent.Search)
                },
                query = viewModel.query,
                onQueryChange = {
                    viewModel.onEvent(GoogleBooksEvent.QueryChanged(it))
                },
                googleBooksUiState = viewModel.googleBooksUiState,
                onBookClicked = {
                    viewModel.onEvent(GoogleBooksEvent.BookClicked(it))
                },
                retryAction = {
                    viewModel.onEvent(GoogleBooksEvent.Search)
                },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            BookDetailsScreen(
                detailedBook = viewModel.currentChosenBook,
                onNavigateUp = {
                    viewModel.onEvent(GoogleBooksEvent.DetailScreenOnNavigateUp)
                },
                canNavigateUp = true,
                modifier = Modifier.fillMaxSize()
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            GoogleBooksHomeScreen(
                onSearchClicked = {
                    viewModel.onEvent(GoogleBooksEvent.Search)
                },
                query = viewModel.query,
                onQueryChange = {
                    viewModel.onEvent(GoogleBooksEvent.QueryChanged(it))
                },
                googleBooksUiState = viewModel.googleBooksUiState,
                onBookClicked = {
                    viewModel.onEvent(GoogleBooksEvent.BookClicked(it))
                },
                retryAction = {
                    viewModel.onEvent(GoogleBooksEvent.Search)
                },
                modifier = Modifier.weight(1f)
            )
            BookDetailsScreen(
                detailedBook = viewModel.currentChosenBook,
                onNavigateUp = {
                    viewModel.onEvent(GoogleBooksEvent.DetailScreenOnNavigateUp)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}



