package com.example.naversearchtest.ui.search

import com.example.naversearchtest.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.naversearchtest.ui.search.SearchContract.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : BaseViewModel<SearchEvent, SearchSideEffect>() {
    override fun handleEvents(event: SearchEvent) {
        when(event){
            is SearchEvent.OnSearchBarClick -> {
                sendSideEffect(SearchSideEffect.NavigateToResult)
            }
        }
    }
}