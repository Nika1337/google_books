package com.example.googlebooks.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.googlebooks.GoogleBooksApplication
import com.example.googlebooks.domain.GetFormattedBooksUseCase
import com.example.googlebooks.domain.GetFormattedDetailedBookUseCase
import com.example.googlebooks.model.ui.Book
import com.example.googlebooks.model.ui.DetailedBook
import com.example.googlebooks.ui.utils.BooksCurrentScreen
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


private const val DEFAULT_QUERY = "android compose"

sealed interface GoogleBooksUiState {
    data class Success(val books: List<Book>) : GoogleBooksUiState
    object Error : GoogleBooksUiState
    object Loading : GoogleBooksUiState
}
class GoogleBooksViewModel(
    private val getFormattedBooksUseCase: GetFormattedBooksUseCase,
    private val getFormattedDetailedBookUseCase: GetFormattedDetailedBookUseCase
) : ViewModel() {
    var googleBooksUiState: GoogleBooksUiState by mutableStateOf(GoogleBooksUiState.Loading)
        private set
    var currentChosenBook: DetailedBook? by mutableStateOf(null)
        private set

    var currentScreen: BooksCurrentScreen by mutableStateOf(BooksCurrentScreen.LIST)
        private set


    var query by mutableStateOf(DEFAULT_QUERY)
        private set

    fun onEvent(event: GoogleBooksEvent) {
        when(event) {
            is GoogleBooksEvent.BookClicked -> {
                chooseBook(event.id)
            }
            is GoogleBooksEvent.Search -> {
                getBooks(query)
            }
            is GoogleBooksEvent.QueryChanged -> {
                query = event.query
            }
            is GoogleBooksEvent.DetailScreenOnNavigateUp -> {
                currentScreen = BooksCurrentScreen.LIST
            }
        }
    }


    init {
        getBooks(DEFAULT_QUERY)
    }

    private fun getBooks(query: String) {
        if (query.isBlank()) {
            googleBooksUiState = GoogleBooksUiState.Success(listOf())
            return
        }
        viewModelScope.launch {
            googleBooksUiState = GoogleBooksUiState.Loading
            googleBooksUiState = try {
                val books = getFormattedBooksUseCase(query)
                GoogleBooksUiState.Success(
                    books = books
                )
            } catch (e: IOException) {
                Log.d("getBooks_IOException", e.toString())
                GoogleBooksUiState.Error
            } catch (e: HttpException) {
                Log.d("getBooks_HttpException", e.toString())
                GoogleBooksUiState.Error
            }
        }
    }

    private fun chooseBook(id: String) {
        viewModelScope.launch {
            currentChosenBook = try {
                val book = getFormattedDetailedBookUseCase(id = id)
                currentScreen = BooksCurrentScreen.DETAIL
                book
            } catch (e: IOException) {
                Log.d("chooseBook_IOException", e.toString())
                null
            } catch (e: HttpException) {
                Log.d("chooseBook_HttpException", e.toString())
                null
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GoogleBooksApplication)
                val getFormattedBooksUseCase = application.container.getFormattedBooksUseCase
                val getFormattedDetailedBookUseCase = application.container.getFormattedDetailedBookUseCase
                GoogleBooksViewModel(
                    getFormattedBooksUseCase = getFormattedBooksUseCase,
                    getFormattedDetailedBookUseCase = getFormattedDetailedBookUseCase
                )
            }
        }
    }
}


