package com.example.naversearchtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val searchLiveData : LiveData<SearchResponse> get() = _searchLiveData

    fun getNaverSearchData(type: String) = viewModelScope.launch {
        val response = getSearchUseCase.execute(type)
        response?.let {
            _searchLiveData.postValue(response!!)
        }
    }

}
