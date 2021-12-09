package com.example.babbelcodingchallenge.wordrescue.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse
import com.example.babbelcodingchallenge.wordrescue.util.Event
import kotlinx.coroutines.launch

class EntryViewModel(private val wordRescueRepository: WordRescueRepository) : ViewModel() {

    var wordDataResponseLiveData = MutableLiveData<Event<WordDataResponse>>()

    fun fetchWords() {
        viewModelScope.launch {
            val wordDataResponse = wordRescueRepository.getWordsData()
            wordDataResponseLiveData.value = Event(wordDataResponse)
        }
    }
}