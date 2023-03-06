package com.example.naversearchtest.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _keyword = MutableStateFlow("")
    private var test = 1

    val pagingData = _keyword
        .filter { k -> k.isNotEmpty() }
        .flatMapLatest {
            getSearchUseCase(it)
        }
        .cachedIn(viewModelScope)

    fun setKeyword(query: String) : Boolean{
        _keyword.value = query
        return false
    }

    fun test() {
        test++
        Log.e("test",test.toString())
    }
}
