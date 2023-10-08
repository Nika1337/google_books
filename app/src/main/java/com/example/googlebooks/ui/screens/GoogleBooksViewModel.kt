package com.example.googlebooks.ui.screens

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
import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.model.Book
import com.example.googlebooks.model.DetailedBook
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
    var currentChosenBook: DetailedBook? = null
        private set
    init {
        getBooks()
    }

    fun getBooks(query: String = "football") {
        viewModelScope.launch {
            googleBooksUiState = GoogleBooksUiState.Loading
            googleBooksUiState = try {
                val result = googleBooksRepository.getBooks(query)
                GoogleBooksUiState.Success(
                    result?.books?.map {
                        Book(
                            id = it.id,
                            thumbnailSrc = it.volumeInfo.imageLinks.thumbnail
                        )
                    } ?: listOf()
                )
            } catch (e: IOException) {
                GoogleBooksUiState.Error
            } catch (e: HttpException) {
                GoogleBooksUiState.Error
            }

        }
    }

    fun chooseBook(id: String) {
        viewModelScope.launch {
            currentChosenBook = try {
                googleBooksRepository.getBook(id)
            } catch (e: IOException) {
                null
            } catch (e: HttpException) {
                null
            }
        }
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


