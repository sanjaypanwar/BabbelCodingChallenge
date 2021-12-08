package com.example.babbelcodingchallenge.wordrescue.data.api

import android.content.Context
import com.example.babbelcodingchallenge.wordrescue.model.WordData
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Type

class LocalWordDataProvider(private val context: Context) {
    fun getWordsData(): WordDataResponse {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("words.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            return WordDataResponse.ERROR
        }

        val listWordType: Type = object : TypeToken<List<WordData>>() {}.type
        return try {
            val wordsList = Gson().fromJson<List<WordData>>(jsonString, listWordType)
            WordDataResponse(wordsList, "")
        } catch (exception: Exception){
            WordDataResponse.ERROR
        }

    }


}