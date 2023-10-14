package com.example.googlebooks.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.googlebooks.GoogleBooksApplication
import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.model.Book
import com.example.googlebooks.model.DetailedBook

sealed interface GoogleBooksUiState {
    data class Success(val books: List<Book>) : GoogleBooksUiState
    object Error : GoogleBooksUiState
    object Loading : GoogleBooksUiState
}
class GoogleBooksViewModel(
    private val googleBooksRepository: GoogleBooksRepository
) : ViewModel() {
    var googleBooksUiState: GoogleBooksUiState by mutableStateOf(GoogleBooksUiState.Loading)
        private set
    var currentChosenBookId: DetailedBook? = null
        private set

    fun onEvent(event: GoogleBooksEvent) {
        when(event) {
            is GoogleBooksEvent.BookClicked -> {
                chooseBook(event.id)
            }
            is GoogleBooksEvent.Search -> {
                getBooks(event.query)
            }
        }
    }


    init {

    }

    private fun getBooks(query: String) {
    }

    private fun chooseBook(id: String) {
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GoogleBooksApplication)
                val googleBooksRepository = application.container.googleBooksRepository
                GoogleBooksViewModel(googleBooksRepository = googleBooksRepository)
            }
        }
    }
}


