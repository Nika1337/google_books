package com.example.googlebooks.network

import com.example.googlebooks.model.api.DetailedBookApiModel
import com.example.googlebooks.model.api.SearchResultApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String) : SearchResultApiModel

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String) : DetailedBookApiModel?
}