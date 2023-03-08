package com.example.naversearchtest.ui

import com.example.naversearchtest.base.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import com.example.naversearchtest.ui.MainContract.*

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseStateViewModel<MainState, MainEvent, MainSideEffect>() {
    override val _viewState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Initial)

    override fun handleEvents(event: MainEvent) {
        when(event){
            is MainEvent.SearchKeyword -> {
//                searchByKeyword(event.query)
            }
        }
    }
}
