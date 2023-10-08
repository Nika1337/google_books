package com.example.googlebooks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlebooks.R
import com.example.googlebooks.ui.screens.GoogleBooksScreen
import com.example.googlebooks.ui.screens.GoogleBooksViewModel
import com.example.googlebooks.ui.theme.GoogleBooksTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksApp() {
    val viewModel: GoogleBooksViewModel = viewModel(factory = GoogleBooksViewModel.Factory)
    Scaffold(
        topBar = {
            GoogleBooksTopAppBar()
        }
    ) { paddingValues ->
        GoogleBooksScreen(
            googleBooksUiState = viewModel.googleBooksUiState,
            onBookClicked = viewModel::chooseBook,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { GoogleBooksBranding() },
        modifier = modifier
    )
}

@Composable
fun GoogleBooksBranding() {
    Image(
        painter = painterResource(id = R.drawable.google_books_logo_2020),
        contentDescription = null
    )
}

@Preview
@Composable
fun GoogleBooksAppPreview() {
    GoogleBooksTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GoogleBooksApp()
        }
    }
}