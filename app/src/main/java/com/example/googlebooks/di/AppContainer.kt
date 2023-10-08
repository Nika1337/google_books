package com.example.googlebooks.di

import com.example.googlebooks.data.GoogleBooksRepository
import com.example.googlebooks.data.NetworkGoogleBooksRepository
import com.example.googlebooks.network.GoogleBooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.Retrofit

interface AppContainer {
    val googleBooksRepository: GoogleBooksRepository
}

class DefaultAppContainer : AppContainer {
    private val baseURL = "https://www.googleapis.com/books/v1/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    private val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }


    override val googleBooksRepository: GoogleBooksRepository by lazy {
        NetworkGoogleBooksRepository(retrofitService)
    }
}