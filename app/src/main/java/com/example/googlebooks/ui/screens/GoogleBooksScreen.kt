
package com.example.googlebooks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.googlebooks.R
import com.example.googlebooks.model.Book


@Composable
fun GoogleBooksScreen(
    googleBooksUiState: GoogleBooksUiState,
    onBookClicked: (id: String) -> Unit,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (googleBooksUiState) {
        is GoogleBooksUiState.Error -> {
            ErrorScreen(
                retryAction = retryAction,
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
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun BookGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookClicked: (String) -> Unit
) {
    // TODO
}

@Composable
fun BookCard(book: Book, onBookClicked: (String) -> Unit, modifier: Modifier = Modifier) {
    // TODO
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}

@Preview
@Composable
fun ErrorScreenPreview(
) {
    ErrorScreen(
        retryAction = {}
    )
}

