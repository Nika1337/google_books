package com.example.googlebooks.domain

import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.model.ui.DetailedBook
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class GetFormattedDetailedBookUseCase(
    private val googleBooksRepository: GoogleBooksRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    open suspend operator fun invoke(id: String) : DetailedBook? =
        withContext(defaultDispatcher) {
            val detailBookApiModel = googleBooksRepository.getBook(id)
            return@withContext if (detailBookApiModel == null) {
                null
            } else {
                DetailedBook(
                    id = detailBookApiModel.id,
                    title = detailBookApiModel.volumeInfo.title,
                    author = if (detailBookApiModel.volumeInfo.authors.isNullOrEmpty()) {
                        null
                    } else {
                        detailBookApiModel.volumeInfo.authors[0]
                    },
                    description = detailBookApiModel.volumeInfo.description,
                    pageCount = detailBookApiModel.volumeInfo.pageCount,
                    mainCategory = detailBookApiModel.volumeInfo.mainCategory ?:
                        if (detailBookApiModel.volumeInfo.categories.isNotEmpty()) {
                            detailBookApiModel.volumeInfo.categories[0]
                        } else {
                            null
                        },
                    averageRating = detailBookApiModel.volumeInfo.averageRating,
                    thumbnailSrc = detailBookApiModel.volumeInfo.imageLinks?.httpsThumbnail ?: ""
                )
            }
        }
}