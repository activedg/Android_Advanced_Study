package com.example.naversearchtest.ui.result

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.naversearchtest.base.BaseStateViewModel
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import com.example.naversearchtest.ui.result.SearchResultContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class SearchResultViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase,
) : BaseStateViewModel<ResultState, ResultEvent, ResultSideEffect>() {
    override val _viewState: MutableStateFlow<ResultState> = MutableStateFlow(ResultState.Initial)

    private val _keyword : MutableStateFlow<String> = MutableStateFlow("")
    val keyword : StateFlow<String> =_keyword.asStateFlow()

    val searchBarShown : StateFlow<Boolean> = _viewState
        .mapLatest { _viewState.value is ResultState.Initial }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val errorShown : StateFlow<Boolean> = _viewState
        .mapLatest { _viewState.value is ResultState.Error }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    val pagingData = _keyword
        .filter { _viewState.value is ResultState.Normal }
        .flatMapLatest {
            // Todo : 빈칸 입력 받았을 때 처리
//            Log.e("keyword", it)
//            if (it.isNullOrEmpty()){
//                setViewState(ResultState.Error("te"))
//                Log.e("value change", _viewState.value.toString())
//            }
            getSearchUseCase(it)
        }
        .cachedIn(viewModelScope)

    private fun setKeyword(value: String){
        _keyword.value = value
    }

    override fun handleEvents(event: ResultEvent) {
        when(event){
            is ResultEvent.SearchKeyword -> {
                if (_viewState.value !is ResultState.Normal){
                    setViewState(ResultState.Normal)
                }
                setKeyword(event.keyword ?: "")
            }
        }
    }
}