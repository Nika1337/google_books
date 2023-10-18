
package com.example.googlebooks.ui.screens.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.googlebooks.R
import com.example.googlebooks.model.ui.Book
import com.example.googlebooks.ui.screens.GoogleBooksUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksHomeScreen(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    googleBooksUiState: GoogleBooksUiState,
    onBookClicked: (id: String) -> Unit,
    retryAction: () -> Unit
) {
    Scaffold(
        topBar = {
            GoogleBooksTopAppBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearchClicked = onSearchClicked,
                height = 56.dp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        GoogleBooksContent(
            googleBooksUiState = googleBooksUiState,
            onBookClicked = onBookClicked,
            retryAction = retryAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        )
    }
}


@Composable
fun GoogleBooksContent(
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
            BooksGrid(
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
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp)
        )
    }
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
        IconButton(onClick = retryAction) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(id = R.string.retry),
                modifier = Modifier.size(64.dp)
            )
        }
    }
}

@Composable
fun BooksGrid(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onBookClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = modifier
    ) {
        items(books) {
            BookCard(
                book = it,
                onBookClicked = onBookClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .aspectRatio(0.75f)
            )
        }
    }
}

@Composable
fun BookCard(book: Book, onBookClicked: (String) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable {
                onBookClicked(book.id)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            model = book.thumbnailSrc,
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.ic_broken_image)
        )
    }
}


@Preview
@Composable
fun BookGridScreenPreview() {
    MaterialTheme {
        Surface {
            BooksGrid(
                books = List(10) {
                    Book(
                        id = "",
                        thumbnailSrc = "",
                        title = "Football Legends"
                    ) },
                onBookClicked = {}
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    MaterialTheme {
        Surface {
            LoadingScreen()
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview(
) {
    MaterialTheme {
        Surface {
            ErrorScreen(
                retryAction = {}
            )
        }
    }
}


