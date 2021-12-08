package com.example.babbelcodingchallenge.wordrescue.di

import com.example.babbelcodingchallenge.wordrescue.viewmodel.EntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordRescueViewModelModule = module {

    viewModel {
        EntryViewModel(get())
    }
}