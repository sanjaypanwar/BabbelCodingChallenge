package com.example.babbelcodingchallenge.wordrescue.di

import android.content.Context
import com.example.babbelcodingchallenge.wordrescue.data.api.LocalWordDataProvider
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository
import org.koin.dsl.module

private const val BASE_URL = "https://api.sumup.com/v0.1/"

val wordRescueModule = module {
    single { provideWordDataProvider(get()) }
    single { provideWordRescueRepository(get()) }
}

fun provideWordDataProvider(context: Context): LocalWordDataProvider {
    return LocalWordDataProvider(context)
}

fun provideWordRescueRepository(localWordDataProvider: LocalWordDataProvider): WordRescueRepository {
    return WordRescueRepository(localWordDataProvider)
}

