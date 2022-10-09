package com.example.composetodo.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TodoItem(
    val title: String,
    val description: String,
    var checked: MutableState<Boolean> = mutableStateOf(false)
)
