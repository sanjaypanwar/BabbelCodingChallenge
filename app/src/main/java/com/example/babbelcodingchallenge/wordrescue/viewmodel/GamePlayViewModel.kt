package com.example.babbelcodingchallenge.wordrescue.viewmodel

import androidx.lifecycle.*
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository
import com.example.babbelcodingchallenge.wordrescue.model.WordData
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse
import com.example.babbelcodingchallenge.wordrescue.util.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class GamePlayViewModel(private val wordRescueRepository: WordRescueRepository) : ViewModel() {
    private var isWaitingTimeRunning: Boolean = false
    val queryLiveData = MutableLiveData<Event<Triple<String, String, Boolean>>>()
    val recentScoreLiveData = MutableLiveData<Event<Int>>()
    private val wordList: List<WordData> = wordRescueRepository.wordList
    var currentScore = 0

    companion object {
        const val TIME_DELAY_WRONG_ANSWER: Long = 1000
    }

    fun fetchRandomQueryData() {
        val shouldShowCorrectOption = (0..1).random() == 1
        if (shouldShowCorrectOption) {
            val queryWordData = wordList[(wordList.indices).random()]
            queryLiveData.value =
                Event(Triple(queryWordData.text_eng, queryWordData.text_spa, true))
        } else {
            val queryWordData = wordList[(wordList.indices).random()]
            val optionWordData = wordList[(wordList.indices).random()]
            queryLiveData.value =
                Event(Triple(queryWordData.text_eng, optionWordData.text_spa, false))
        }
    }

    fun onCorrectOptionSelected() {
        if (!isWaitingTimeRunning) {
            fetchRandomQueryData()
            currentScore++
        }
    }

    fun onWrongOptionSelected() {
        viewModelScope.launch {
            isWaitingTimeRunning = true
            delay(TIME_DELAY_WRONG_ANSWER)
            isWaitingTimeRunning = false
            if (wordRescueRepository.highestScore < currentScore) {
                wordRescueRepository.highestScore = currentScore
            }
            recentScoreLiveData.value = Event(currentScore)
        }

    }

}