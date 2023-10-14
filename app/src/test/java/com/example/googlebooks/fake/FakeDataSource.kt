package com.example.googlebooks.fake

import com.example.googlebooks.model.Book
import com.example.googlebooks.model.ImageLinks
import com.example.googlebooks.model.SearchResult
import com.example.googlebooks.model.VolumeInfo

object FakeDataSource {

    private const val idOne = "img1"
    private const val idTwo = "img2"
    private const val idThree = "img3"
    private const val idFour = "img4"
    private const val imgOne = "url1"
    private const val imgTwo = "url2"
    private const val imgThree = "url3"
    private const val imgFour = "url4"

    val booksList = listOf(
        Book(
            id = idOne,
            volumeInfo = VolumeInfo(ImageLinks(imgOne))
        ),
        Book(
            id = idTwo,
            volumeInfo = VolumeInfo(ImageLinks(imgTwo))
        ),
        Book(
            id = idThree,
            volumeInfo = VolumeInfo(ImageLinks(imgThree))
        ),
        Book(
            id = idFour,
            volumeInfo = VolumeInfo(ImageLinks(imgFour))
        )
    )

    val searchResult = SearchResult(
        books = booksList
    )
}