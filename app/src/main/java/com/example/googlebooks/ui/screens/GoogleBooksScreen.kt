@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.googlebooks.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.googlebooks.model.Book


@Composable
fun GoogleBooksScreen(
    modifier: Modifier = Modifier,
    googleBooksUiState: GoogleBooksUiState,
    onBookClicked: (String) -> Unit // TODO BOOK MODEL
) {
    when (googleBooksUiState) {
        is GoogleBooksUiState.Error -> {
            ErrorScreen(
                modifier = modifier
            )
        }
        is GoogleBooksUiState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }
        is GoogleBooksUiState.Success -> {
            BookGridScreen(
                books = googleBooksUiState.books,
                onBookClicked = onBookClicked,
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    // TODO
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    // TODO
}

@Composable
fun BookGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookClicked: (String) -> Unit
) {

}


@Preview
@Composable
fun BookListScreenPreview(
) {
    GoogleBooksScreen(
        googleBooksUiState = GoogleBooksUiState.Success(
            listOf()
        ),
        onBookClicked = {}
    )
}

