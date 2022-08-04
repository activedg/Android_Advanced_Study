package com.example.naversearchtest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naversearchtest.domain.model.KeywordGroup
import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _searchLiveData = MutableLiveData<SearchResponse>()
    val searchLiveData: LiveData<SearchResponse> get() = _searchLiveData

    fun getNaverSearchData(keyword: String) {
        val searchData = SearchData(
            "2022-07-04",
            "2022-08-04",
            "month",
            listOf(KeywordGroup(keyword, listOf(keyword)))
        )
        viewModelScope.launch {
            val response = getSearchUseCase.execute(searchData)
            Log.e("response", response.toString())
            response?.let {
                _searchLiveData.postValue(response!!)
            }
        }
    }

}
