package com.example.naversearchtest.ui.search

import com.example.naversearchtest.base.ViewModelType

class SearchContract {
    sealed class SearchEvent: ViewModelType.Event{
        object OnSearchBarClick : SearchEvent()
    }

    sealed class SearchSideEffect: ViewModelType.SideEffect{
        object NavigateToResult : SearchSideEffect()
    }
}