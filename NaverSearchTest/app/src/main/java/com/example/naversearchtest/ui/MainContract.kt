package com.example.naversearchtest.ui

import com.example.naversearchtest.base.ViewModelType

class MainContract{
    sealed class MainState : ViewModelType.State{
        object Initial : MainState()
        object Loading : MainState()
        data class Success(private val data: String) : MainState()
    }

    sealed class MainEvent: ViewModelType.Event{
        data class SearchKeyword(val query: String) : MainEvent()
    }

    sealed class MainSideEffect : ViewModelType.SideEffect{
        object NavigateToHome : MainSideEffect()
        object NavigateToProfile : MainSideEffect()
    }
}
