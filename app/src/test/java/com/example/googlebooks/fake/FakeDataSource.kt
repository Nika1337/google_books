package com.example.googlebooks.fake

import com.example.googlebooks.model.api.BookApiModel
import com.example.googlebooks.model.api.DetailedBookApiModel
import com.example.googlebooks.model.api.DetailedImageLinksApiModel
import com.example.googlebooks.model.api.DetailedVolumeInfoApiModel
import com.example.googlebooks.model.api.ImageLinksApiModel
import com.example.googlebooks.model.api.SaleInfoApiModel
import com.example.googlebooks.model.api.SearchResultApiModel
import com.example.googlebooks.model.api.VolumeInfoApiModel
import com.example.googlebooks.model.ui.Book
import com.example.googlebooks.model.ui.DetailedBook

object FakeDataSource {

    private const val idOne = "img1"
    private const val idTwo = "img2"
    private const val idThree = "img3"
    private const val idFour = "img4"

    private const val imgOne = "url1"
    private const val imgTwo = "url2"
    private const val imgThree = "url3"
    private const val imgFour = "url4"

    private const val titleOne = "title1"
    private const val titleTwo = "title2"
    private const val titleThree = "title3"
    private const val titleFour = "title4"

    val bookApiModelList = listOf(
        BookApiModel(
            id = idOne,
            volumeInfo = VolumeInfoApiModel(
                title = titleOne,
                imageLinksApiModel = ImageLinksApiModel(imgOne)
            )
        ),
        BookApiModel(
            id = idTwo,
            volumeInfo = VolumeInfoApiModel(
                title = titleTwo,
                imageLinksApiModel = ImageLinksApiModel(imgTwo)
            )
        ),
        BookApiModel(
            id = idThree,
            volumeInfo = VolumeInfoApiModel(
                title = titleThree,
                imageLinksApiModel = ImageLinksApiModel(imgThree)
            )
        ),
        BookApiModel(
            id = idFour,
            volumeInfo = VolumeInfoApiModel(
                title = titleFour,
                imageLinksApiModel = ImageLinksApiModel(imgFour)
            )
        )
    )
    val booksList = listOf(
        Book(
            id = idOne,
            thumbnailSrc = imgOne,
            title = titleOne
        ),
        Book(
            id = idTwo,
            thumbnailSrc = imgTwo,
            title = titleTwo
        ),
        Book(
            id = idThree,
            thumbnailSrc = imgThree,
            title = titleThree
        ),
        Book(
            id = idFour,
            thumbnailSrc = imgFour,
            title = titleFour
        ),
    )

    val searchResultApiModel = SearchResultApiModel(
        books = bookApiModelList
    )
    val detailedBookApiModel = DetailedBookApiModel(
        id = idOne,
        volumeInfo = DetailedVolumeInfoApiModel(
            title = titleOne,
            publishedDate = "20/12/2004",
            pageCount = 203,
            mainCategory = "Football",
            categories = listOf("Football"),
            language = "en",
            imageLinks = DetailedImageLinksApiModel(
                smallThumbnail = imgOne,
                thumbnail = imgOne
            )
        ),
        saleInfo = SaleInfoApiModel(
            country = "",
            saleability = "",
            isEbook = false
        )
    )

    val detailedBook = DetailedBook(
        id = idOne,
        title = titleOne,
        author = null,
        description = null,
        pageCount = 203,
        mainCategory = "Football",
        thumbnailSrc = imgOne,
        averageRating = null
    )
    fun convertBookApiModelToDetailedBookApiModel(bookApiModel: BookApiModel) : DetailedBookApiModel =
        DetailedBookApiModel(
            id = bookApiModel.id,
            volumeInfo = DetailedVolumeInfoApiModel(
                title = bookApiModel.volumeInfo.title,
                publishedDate = "",
                pageCount = 100,
                categories = listOf(),
                language = "",
                imageLinks = DetailedImageLinksApiModel(
                    smallThumbnail = "",
                    thumbnail = bookApiModel.httpsThumbnail.replace("https","http")
                )
            ),
            saleInfo = SaleInfoApiModel(
                country = "",
                saleability = "",
                isEbook = false
            )
        )
}