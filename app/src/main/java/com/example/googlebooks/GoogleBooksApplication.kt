package com.example.googlebooks

import android.app.Application
import com.example.googlebooks.di.AppContainer
import com.example.googlebooks.di.DefaultAppContainer

class GoogleBooksApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}