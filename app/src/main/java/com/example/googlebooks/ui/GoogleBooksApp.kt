package com.example.googlebooks.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlebooks.ui.screens.GoogleBooksEvent
import com.example.googlebooks.ui.screens.GoogleBooksScreen
import com.example.googlebooks.ui.screens.GoogleBooksTopAppBar
import com.example.googlebooks.ui.screens.GoogleBooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksApp() {
    val viewModel: GoogleBooksViewModel = viewModel(factory = GoogleBooksViewModel.Factory)
    Scaffold(
        topBar = {
            GoogleBooksTopAppBar(
                onSearchClicked = {
                    viewModel
                        .onEvent(GoogleBooksEvent.Search(it))
                },
                height = 56.dp
            )
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



