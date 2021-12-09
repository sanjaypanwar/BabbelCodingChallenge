package com.example.babbelcodingchallenge.wordrescue.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.babbelcodingchallenge.R
import com.example.babbelcodingchallenge.databinding.EntryFragmentBinding
import com.example.babbelcodingchallenge.databinding.FinalScoreFragmentBinding
import com.example.babbelcodingchallenge.wordrescue.viewmodel.EntryViewModel
import com.example.babbelcodingchallenge.wordrescue.viewmodel.FinalScoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinalScoreFragment : Fragment() {
    private val finalScoreViewModel: FinalScoreViewModel by viewModel()
    private lateinit var binding: FinalScoreFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.final_score_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setObserver()
    }

    private fun setObserver() {
    }

    private fun setUpUi() {

    }
}