package com.example.babbelcodingchallenge.wordrescue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordData (
    @SerializedName("text_eng") val text_eng: String,
    @SerializedName("text_spa") val text_spa: String,
): Parcelable