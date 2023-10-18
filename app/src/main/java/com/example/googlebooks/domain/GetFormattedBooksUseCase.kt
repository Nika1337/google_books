package com.example.googlebooks.domain

import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.model.ui.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class GetFormattedBooksUseCase(
    private val googleBooksRepository: GoogleBooksRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    open suspend operator fun invoke(query: String) : List<Book> =
        withContext(defaultDispatcher) {
            val bookApiModelList = googleBooksRepository.getBooks(query)
            return@withContext bookApiModelList.books.map {
                Book(
                    id = it.id,
                    thumbnailSrc = it.httpsThumbnail,
                    title = it.volumeInfo.title
                )
            }
        }
}