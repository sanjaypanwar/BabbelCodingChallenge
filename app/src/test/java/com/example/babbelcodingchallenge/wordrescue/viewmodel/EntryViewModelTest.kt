package com.example.babbelcodingchallenge.wordrescue.viewmodel


import com.example.babbelcodingchallenge.BaseViewModelTest
import com.example.babbelcodingchallenge.wordrescue.data.repository.WordRescueRepository
import com.example.babbelcodingchallenge.wordrescue.model.WordData
import com.example.babbelcodingchallenge.wordrescue.model.WordDataResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class EntryViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: EntryViewModel
    private val wordRescueRepository = mockk<WordRescueRepository>()

    @ExperimentalCoroutinesApi
    override fun baseSetUp() {
        super.baseSetUp()
        viewModel = EntryViewModel(wordRescueRepository)
    }

    @Test
    fun fetchWords(): Unit = runBlocking {
        val wordData = WordData("text_eng", "text_spa")
        val mockList: List<WordData> = mutableListOf(wordData)
        coEvery { wordRescueRepository.getWordsData() } returns (
                WordDataResponse(mockList, "")
                )
        viewModel.fetchWords()
        viewModel.wordDataResponseLiveData.value?.getContentIfNotHandled()?.wordDataList?.get(0)
            ?.let { assertEquals(it.text_eng, "text_eng") }
    }
}