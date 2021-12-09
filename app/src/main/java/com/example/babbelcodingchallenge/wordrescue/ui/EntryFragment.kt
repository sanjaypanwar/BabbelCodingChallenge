package com.example.babbelcodingchallenge.wordrescue.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.babbelcodingchallenge.R
import com.example.babbelcodingchallenge.databinding.EntryFragmentBinding
import com.example.babbelcodingchallenge.wordrescue.viewmodel.EntryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class EntryFragment : Fragment() {
    private val entryViewModel: EntryViewModel by viewModel()
    private lateinit var binding: EntryFragmentBinding

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
            val response = it.getContentIfNotHandled()
            if (response != null) if (response.errorString.isEmpty()) {
                view?.let { view ->
                    Navigation.findNavController(view)
                        .navigate(R.id.action_entryFragment_to_gamePlayFragment)
                }
            }
        }
    }

    private fun setUpUi() {
        binding.buttonStartGame.setOnClickListener {
            entryViewModel.fetchWords()
        }
    }
}