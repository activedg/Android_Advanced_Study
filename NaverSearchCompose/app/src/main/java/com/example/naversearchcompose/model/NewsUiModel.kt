package com.example.naversearchcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsUiModel(
    val pubDate: String = "",
    val title: String = "",
    val description: String = "",
    val link: String = "",
): Parcelable
