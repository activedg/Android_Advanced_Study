package com.example.naversearchcompose.ui.main

import androidx.lifecycle.SavedStateHandle
import com.example.naversearchcompose.base.BaseViewModel
import com.example.naversearchcompose.domain.usecase.ClearNewsPageConfigUseCase
import com.example.naversearchcompose.domain.usecase.FetchNewsListUseCase
import com.example.naversearchcompose.model.NewsUiModel
import com.example.naversearchcompose.model.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.naversearchcompose.ui.main.MainContract.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchNewsListUseCase: FetchNewsListUseCase,
    private val clearNewsPageConfigUseCase: ClearNewsPageConfigUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel<MainState, MainSideEffect>(savedStateHandle){
    override fun createInitialState(): MainState = MainState()

    init {
        load()
    }

    fun load() = intent{
        if (state.pageState == PageState.EXHAUST) return@intent

        reduce { state.copy(pageState = PageState.LOADING) }


        runCatching {
            fetchNewsListUseCase("네이버")
        }.onSuccess {
            reduce {
                state.copy(
                    newsList = state.newsList + it.map {
                        NewsUiModel(
                            pubDate = it.pubDate,
                            title = it.title,
                            description = it.description,
                            link = it.link
                        )
                    },
                    pageState = if (it.size in 0 until 20) PageState.EXHAUST else PageState.IDLE
                )
            }
        }.onFailure {
            reduce { state.copy(pageState = PageState.ERROR) }
        }
    }

    override fun onCleared() {
        CoroutineScope(Dispatchers.Main).launch {
            clearNewsPageConfigUseCase()
        }
        super.onCleared()
    }

}