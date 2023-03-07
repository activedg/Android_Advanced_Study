package com.example.naversearchtest.ui

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.naversearchtest.base.BaseViewModel
import com.example.naversearchtest.domain.usecase.GetSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.example.naversearchtest.ui.MainContract.*

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : BaseViewModel<MainState, MainSideEffect, MainEvent>() {
    override val _viewState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Initial)

    private val _keyword = MutableStateFlow("")
    val keword = _keyword.asStateFlow()

    val pagingData = _keyword
        .filter { k -> k.isNotEmpty() }
        .flatMapLatest {
            getSearchUseCase(it)
        }
        .cachedIn(viewModelScope)

    private fun searchByKeyword(query: String) {
        _keyword.value = query
    }

    override fun handleEvents(event: MainEvent) {
        when(event){
            is MainEvent.SearchKeyword -> {
                searchByKeyword(event.query)
            }
        }
    }
}
