package com.example.naversearchtest.ui.result

import com.example.naversearchtest.base.ViewModelType

class SearchResultContract {
    sealed class ResultState: ViewModelType.State{
        object Initial: ResultState()
        object Normal: ResultState()
        data class Error(val msg: String?) : ResultState()
    }

    sealed class ResultEvent: ViewModelType.Event{
        data class SearchKeyword(val keyword: String?) : ResultEvent()
    }

    sealed class ResultSideEffect: ViewModelType.SideEffect{
    }
}