package com.example.babbelcodingchallenge.wordrescue.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository

class FinalScoreViewModel(private val wordRescueRepository: WordRescueRepository) : ViewModel() {

    var bestScoreLiveData = MutableLiveData<Int>()

    fun getBestScore() {
        bestScoreLiveData.value = wordRescueRepository.highestScore
    }

}