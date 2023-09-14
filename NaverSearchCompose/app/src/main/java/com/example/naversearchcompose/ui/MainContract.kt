package com.example.naversearchcompose.ui

class MainContract {
    data class MainState(
        val id: String = ""
    )

    sealed interface MainSideEffect {
        data object NavigateToDetail: MainSideEffect
    }
}