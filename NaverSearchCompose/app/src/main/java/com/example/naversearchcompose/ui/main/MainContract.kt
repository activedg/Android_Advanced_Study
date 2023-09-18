package com.example.naversearchcompose.ui.main

import android.os.Parcelable
import com.example.naversearchcompose.model.NewsUiModel
import com.example.naversearchcompose.model.PageState
import kotlinx.parcelize.Parcelize

class MainContract {
    @Parcelize
    data class MainState(
        val newsList: List<NewsUiModel> = emptyList(),
        val pageState: PageState = PageState.IDLE
    ): Parcelable

    sealed interface MainSideEffect {
        data object NavigateToDetail: MainSideEffect
    }
}