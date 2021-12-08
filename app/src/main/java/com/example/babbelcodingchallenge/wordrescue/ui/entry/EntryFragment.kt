package com.example.babbelcodingchallenge.wordrescue.ui.entry

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.babbelcodingchallenge.R
import com.example.babbelcodingchallenge.databinding.EntryFragmentBinding
import com.example.babbelcodingchallenge.wordrescue.viewmodel.EntryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class EntryFragment : Fragment() {
    private val entryViewModel: EntryViewModel by viewModel()
    private lateinit var binding: EntryFragmentBinding
    private lateinit var viewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.entry_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setObserver()
    }

    private fun setObserver() {
        entryViewModel.wordDataResponseLiveData.observe(viewLifecycleOwner) {
            if (it.errorString.isEmpty()) {
                Log.ERROR
            } else {
                // Start next fragment
            }
        }
    }

    private fun setUpUi() {
        binding.buttonStartGame.setOnClickListener {
            entryViewModel.fetchWords()
        }
    }
}