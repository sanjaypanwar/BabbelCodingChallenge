package com.example.babbelcodingchallenge.wordrescue

import android.app.Application
import com.example.babbelcodingchallenge.wordrescue.di.wordRescueModule
import com.example.babbelcodingchallenge.wordrescue.di.wordRescueViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApplication)
            modules(listOf(wordRescueViewModelModule, wordRescueModule))
        }
    }

}