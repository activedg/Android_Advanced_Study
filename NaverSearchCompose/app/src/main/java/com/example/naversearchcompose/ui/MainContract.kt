package com.example.naversearchcompose.ui

import com.example.naversearchcompose.domain.model.News
import com.example.naversearchcompose.model.PageState

class MainContract {
    data class MainState(
        val newsList: List<News> = emptyList(),
        val pageState: PageState = PageState.IDLE
    )

    sealed interface MainSideEffect {
        data object NavigateToDetail: MainSideEffect
    }
}