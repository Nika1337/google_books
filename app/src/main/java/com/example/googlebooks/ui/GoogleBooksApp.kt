package com.example.googlebooks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlebooks.R
import com.example.googlebooks.ui.screens.GoogleBooksScreen
import com.example.googlebooks.ui.screens.GoogleBooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksApp() {
    val viewModel: GoogleBooksViewModel = viewModel(factory = GoogleBooksViewModel.Factory)
    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        GoogleBooksScreen(
            googleBooksUiState = viewModel.googleBooksUiState,
            onBookClicked = {  },
            retryAction = {  },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun GoogleBooksTopAppBar(modifier: Modifier = Modifier) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    oneSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            GoogleBooksBranding()
        },
        actions = {
            IconButton(
                onClick = oneSearchClicked
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = Color.Black
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun GoogleBooksBranding(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.google_books_logo_2020),
        contentDescription = stringResource(R.string.google_books),
        modifier = modifier
    )
}


@Preview
@Composable
fun DefaultTopAppBarPreview() {
    DefaultTopAppBar(
        oneSearchClicked = {}
    )
}

