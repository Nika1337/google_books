package com.example.googlebooks.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.googlebooks.R
import com.example.googlebooks.ui.screens.GoogleBooksEvent
import com.example.googlebooks.ui.screens.GoogleBooksScreen
import com.example.googlebooks.ui.screens.GoogleBooksViewModel
import com.example.googlebooks.ui.screens.SearchWidgetState
import com.example.googlebooks.ui.theme.GoogleBooksTheme

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

@Composable
fun GoogleBooksTopAppBar(
    onSearchClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 64.dp
) {
    var searchWidgetState by rememberSaveable {
        mutableStateOf(SearchWidgetState.CLOSED)
    }
    when(searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultTopAppBar(
                oneSearchClicked = { searchWidgetState = SearchWidgetState.OPENED },
                modifier = modifier
                    .height(height)
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                onSearchClicked = onSearchClicked,
                onCloseClicked = {
                    searchWidgetState = SearchWidgetState.CLOSED
                },
                modifier = modifier
                    .height(height)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    oneSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            GoogleBooksBranding(
                modifier = Modifier.fillMaxHeight()
            )
        },
        actions = {
            Box(
               contentAlignment = Alignment.CenterEnd,
            ){
                IconButton(
                    onClick = oneSearchClicked,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon),
                    )
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun GoogleBooksBranding(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.google_books_logo_2020),
        contentDescription = stringResource(R.string.google_books),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                   text = "Search here...",
                   fontWeight = FontWeight.Normal
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    onClick = {
                        onSearchClicked(text)
                    }
                    ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            onCloseClicked()
                        }
                    },
                    modifier = Modifier
                        .padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Preview
@Composable
fun DefaultTopAppBarPreview() {
    GoogleBooksTheme {
        Surface {
            DefaultTopAppBar(
                oneSearchClicked = {}
            )
        }
    }
}

@Preview
@Composable
fun SearchTopAppBarPreview() {
    GoogleBooksTheme {
        Surface {
            SearchAppBar(onSearchClicked = {}, onCloseClicked = { })
        }
    }
}

