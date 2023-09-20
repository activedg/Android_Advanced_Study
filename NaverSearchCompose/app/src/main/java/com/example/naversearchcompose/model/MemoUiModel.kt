package com.example.naversearchcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemoUiModel(
    val id: Int = 0,
    val content: String = "",
    val isRead: Boolean = false
): Parcelable
