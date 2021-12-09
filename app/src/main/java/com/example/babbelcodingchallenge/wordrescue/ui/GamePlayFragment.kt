package com.example.babbelcodingchallenge.wordrescue.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.babbelcodingchallenge.R
import com.example.babbelcodingchallenge.databinding.GamePlayFragmentBinding
import com.example.babbelcodingchallenge.wordrescue.viewmodel.GamePlayViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

import android.util.DisplayMetrics
import androidx.navigation.Navigation
import android.animation.ValueAnimator
import com.google.android.material.snackbar.Snackbar


class GamePlayFragment : Fragment(), Animator.AnimatorListener {
    private var isAnimationEndDueToCurrentAnswer: Boolean = false
    private var playStoppedDueToTimeOut: Boolean = false
    private lateinit var animation: ObjectAnimator
    private var screenHeightPixel: Int = 1080
    private var isCurrentPlayAnswerIsCorrectText: Boolean = false
    private var isPlaying: Boolean = false
    private lateinit var binding: GamePlayFragmentBinding
    private val gamePlayViewModel: GamePlayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.game_play_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setObserver()
        gamePlayViewModel.fetchRandomQueryData()
        screenHeightPixel = getScreenHeightPixel()
    }

    @SuppressLint("NewApi")
    private fun getScreenHeightPixel(): Int {
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        return metrics.heightPixels

    }

    private fun setObserver() {
        gamePlayViewModel.queryLiveData.observe(viewLifecycleOwner) {
            val queryData = it.getContentIfNotHandled()
            if (queryData != null) {
                binding.textOptionWord.bringToFront()
                binding.textOptionWord.text = queryData.second
                binding.textQuestionWord.text = queryData.first
                startSinglePlay(queryData.third)
            }
        }

        gamePlayViewModel.recentScoreLiveData.observe(viewLifecycleOwner) {
            val argsToPass = Bundle()
            argsToPass.putInt("finalScore", gamePlayViewModel.currentScore)
            view?.let { view ->
                Navigation.findNavController(view)
                    .navigate(R.id.action_gamePlayFragment_to_finalScoreFragment, argsToPass)
            }
        }
    }

    private fun startSinglePlay(isCorrectOption: Boolean) {
        isCurrentPlayAnswerIsCorrectText = isCorrectOption
        isPlaying = true
        startAnimation()
    }

    private fun stopSinglePlay() {
        isPlaying = false
        stopAnimation()
    }

    private fun stopAnimation() {
        animation.pause()
    }

    private fun startAnimation() {
        animation = ObjectAnimator.ofFloat(
            binding.textOptionWord,
            "translationY",
            screenHeightPixel.toFloat()
        ).apply {
            duration = 5 * 1000
            start()
        }
        isAnimationEndDueToCurrentAnswer = false
        animation.addListener(this@GamePlayFragment)
    }

    private fun setUpUi() {

        binding.buttonCorrect.setOnClickListener {
            stopSinglePlay()
            if (isCurrentPlayAnswerIsCorrectText) {
                onCorrectOptionSelected()
            } else {
                onWrongOptionSelected()
            }
        }
        binding.buttonWrong.setOnClickListener {
            stopSinglePlay()
            if (!isCurrentPlayAnswerIsCorrectText) {
                onCorrectOptionSelected()
            } else {
                onWrongOptionSelected()
            }
        }

    }

    private fun onWrongOptionSelected() {
        if (!playStoppedDueToTimeOut) {
            if (isCurrentPlayAnswerIsCorrectText) {
                context?.let { ContextCompat.getColor(it, R.color.color_correct_option) }?.let {
                    binding.buttonCorrect.setBackgroundColor(
                        it
                    )
                }
                context?.let { ContextCompat.getColor(it, R.color.color_wrong_option) }?.let {
                    binding.buttonWrong.setBackgroundColor(
                        it
                    )
                }
            } else {
                context?.let { ContextCompat.getColor(it, R.color.color_correct_option) }?.let {
                    binding.buttonWrong.setBackgroundColor(
                        it
                    )
                }
                context?.let { ContextCompat.getColor(it, R.color.color_wrong_option) }?.let {
                    binding.buttonCorrect.setBackgroundColor(
                        it
                    )
                }
            }
        }
        gamePlayViewModel.onWrongOptionSelected()
    }

    private fun onCorrectOptionSelected() {
        view?.let {
            Snackbar.make(it, "Right Answer", Snackbar.LENGTH_SHORT).setDuration(500).show()
        }
        isAnimationEndDueToCurrentAnswer = true
        animation.cancel()
        gamePlayViewModel.onCorrectOptionSelected()
    }

    override fun onAnimationStart(p0: Animator?) {
        isAnimationEndDueToCurrentAnswer = false
    }

    override fun onAnimationEnd(p0: Animator?) {
        if (!isAnimationEndDueToCurrentAnswer) {
            playStoppedDueToTimeOut = true
            onWrongOptionSelected()
        } else {
            animation.removeListener(this)
            animation.duration = 0
            (animation as ValueAnimator).reverse()
        }
    }

    override fun onAnimationCancel(p0: Animator?) {

    }

    override fun onAnimationRepeat(p0: Animator?) {

    }


}