package com.example.babbelcodingchallenge.wordrescue.viewmodel

import androidx.lifecycle.*
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EntryViewModel(private val wordRescueRepository: WordRescueRepository) : ViewModel() {

    var wordDataResponseLiveData: MutableLiveData<WordDataResponse> = MutableLiveData()

    fun fetchWords() {
        viewModelScope.launch {
            val wordDataResponse = wordRescueRepository.getWordsData()
            wordDataResponseLiveData.value = wordDataResponse
        }
    }
}