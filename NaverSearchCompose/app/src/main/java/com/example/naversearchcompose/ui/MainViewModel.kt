package com.example.naversearchcompose.ui

import com.example.naversearchcompose.base.BaseViewModel
import com.example.naversearchcompose.domain.usecase.FetchNewsListUseCase
import com.example.naversearchcompose.model.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.naversearchcompose.ui.MainContract.*
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchNewsListUseCase: FetchNewsListUseCase
): BaseViewModel<MainState, MainSideEffect>(){
    override fun createInitialState(): MainState = MainState()

    init {
        load()
    }

    fun load() = intent{
        if (state.pageState == PageState.EXHAUST) return@intent

        launchWithLoading {
            reduce { state.copy(pageState = PageState.LOADING) }

            runCatching {
                fetchNewsListUseCase("네이버")
            }.onSuccess {
                reduce {
                    state.copy(
                        newsList = state.newsList + it,
                        pageState = if (it.size in 0 until 20) PageState.EXHAUST else PageState.IDLE
                    )
                }
            }.onFailure {
                reduce { state.copy(pageState = PageState.ERROR) }
            }
        }
    }

}