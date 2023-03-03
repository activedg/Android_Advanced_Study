package com.example.naversearchtest.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.naversearchtest.domain.entity.NewsItem
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.switchMap
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _newsPagingData = MutableStateFlow<PagingData<NewsItem>>(PagingData.empty())
    val newsPagingData : StateFlow<PagingData<NewsItem>> get() = _newsPagingData

    private val _keyword = MutableLiveData<String>()
    val keyword : LiveData<String> = _keyword

    val paging = _keyword.switchMap {
        getSearchUseCase(it).cachedIn(viewModelScope).asLiveData()
    }

    fun setKeyword(keyword: String){
        _keyword.value = keyword
    }

//    fun getSearchData(keyword: String?) : Boolean{
//        keyword?.let {
//            getSearchUseCase(keyword)
//                .catch {
//                    Log.e("okhttpError", it.toString())
//                }
//                .onEach {
//                    _newsPagingData.value = it
//                }
//                .launchIn(viewModelScope)
//
//            // cachedIn을 사용하여 캐싱 가능
//        }
//
//        return false
//    }

//    fun getSearchData(keyword: String) : Flow<PagingData<NewsItem>> {
//        return getSearchData(keyword).cachedIn(viewModelScope)
//    }
}
