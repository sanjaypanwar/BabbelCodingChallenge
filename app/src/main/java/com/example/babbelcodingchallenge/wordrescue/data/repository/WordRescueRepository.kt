package com.example.babbelcodingchallenge.wordrescue.data.repository

import com.example.babbelcodingchallenge.wordrescue.data.api.LocalWordDataProvider
import com.example.babbelcodingchallenge.wordrescue.model.WordData
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse

class WordRescueRepository(private val localWordDataProvider: LocalWordDataProvider) {
    lateinit var wordMap: Map<String, String>
    lateinit var wordList: List<WordData>
    var highestScore = 0
    suspend fun getWordsData(): WordDataResponse {
        val response = localWordDataProvider.getWordsData()
        if (response.wordDataList.isNotEmpty()) {
            wordList = response.wordDataList
            wordMap = convertToMap(wordList)
        }
        return response
    }

    private fun convertToMap(wordList: List<WordData>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        wordList.forEach {
            map[it.text_eng] = it.text_spa
        }
        return map
    }
}