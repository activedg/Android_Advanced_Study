package com.example.samplelistadapter

import androidx.lifecycle.ViewModel
import com.example.samplelistadapter.model.Text
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _textList = MutableStateFlow<List<Text>>(emptyList())
    val textList : StateFlow<List<Text>>
        get() = _textList

    init {
        _textList.value = listOf(
            Text(1, "테스트1", "테스트 테스트 테스트"),
            Text(2, "테스트2", "테스트 테스트 테스트"),
            Text(3, "테스트3", "테스트 테스트 테스트"),
        )
    }

    fun deleteLastItem(){
        _textList.value = _textList.value.dropLast(1)
    }
}