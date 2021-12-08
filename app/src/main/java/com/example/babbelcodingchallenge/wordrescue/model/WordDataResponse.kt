package com.example.babbelcodingchallenge.wordrescue.model

data class WordDataResponse(val wordDataList: List<WordData>, val errorString: String){
    companion object{
        private const val errorStringResponse = "Error in fetching word data."
        val ERROR = WordDataResponse(listOf(), errorStringResponse)
    }
}
