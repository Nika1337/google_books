package com.example.googlebooks.ui.screens.books

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.googlebooks.R
import com.example.googlebooks.ui.theme.GoogleBooksTheme

@Composable
fun GoogleBooksTopAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
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
                query = query,
                onQueryChange = onQueryChange,
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
        modifier = modifier
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
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.search_here),
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
                        onSearchClicked()
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
                        if (query.isNotEmpty()) {
                            onQueryChange("")
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
                onSearch = {
                    onSearchClicked()
                    focusManager.clearFocus()
                }
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
                oneSearchClicked = {},
                modifier = Modifier.height(56.dp)
            )
        }
    }
}

@Preview
@Composable
fun SearchTopAppBarPreview() {
    GoogleBooksTheme {
        Surface {
            SearchAppBar(
                query = "",
                onQueryChange = {},
                onSearchClicked = {},
                onCloseClicked = {},
                modifier = Modifier.height(56.dp)
            )
        }
    }
}