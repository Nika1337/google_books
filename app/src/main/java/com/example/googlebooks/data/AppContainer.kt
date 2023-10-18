package com.example.googlebooks.data

import com.example.googlebooks.domain.GetFormattedBooksUseCase
import com.example.googlebooks.domain.GetFormattedDetailedBookUseCase
import com.example.googlebooks.network.GoogleBooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.Retrofit

interface AppContainer {
    val getFormattedBooksUseCase: GetFormattedBooksUseCase
    val getFormattedDetailedBookUseCase: GetFormattedDetailedBookUseCase
}

class DefaultAppContainer : AppContainer {
    private val baseURL = "https://www.googleapis.com/books/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    private val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }


    private val googleBooksRepository: GoogleBooksRepository by lazy {
        NetworkGoogleBooksRepository(retrofitService)
    }
    override val getFormattedBooksUseCase: GetFormattedBooksUseCase by lazy {
        GetFormattedBooksUseCase(googleBooksRepository =  googleBooksRepository)
    }
    override val getFormattedDetailedBookUseCase: GetFormattedDetailedBookUseCase by lazy {
        GetFormattedDetailedBookUseCase(googleBooksRepository = googleBooksRepository)
    }
}