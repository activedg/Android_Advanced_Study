package com.example.mygithubsample.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : ViewModelType.Event, SE : ViewModelType.SideEffect> : ViewModel() {
    // 화면 전체에 대한 State 가 필요 없는 경우
    private val _sideEffect : Channel<SE> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    protected fun sendSideEffect(effect: SE){
        viewModelScope.launch {
            _sideEffect.send(effect)
        }
    }

    fun setEvent(event: E){
        handleEvents(event)
    }

    abstract fun handleEvents(event: E)
}