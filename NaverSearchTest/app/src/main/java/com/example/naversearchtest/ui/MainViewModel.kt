package com.example.naversearchtest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naversearchtest.domain.entity.NewsItem
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _newsData = MutableStateFlow(mutableListOf<NewsItem>())
    val newsData : StateFlow<List<NewsItem>> get() = _newsData

    fun getSearchData(keyword: String?) : Boolean{
        keyword?.let {
            getSearchUseCase(keyword)
                .onEach {
                    _newsData.value = it.items as MutableList<NewsItem>
                    Log.e("searchResult", it.toString())
                }
                .launchIn(viewModelScope)
        }

        return false
    }
}
