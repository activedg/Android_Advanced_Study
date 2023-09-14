package com.example.naversearchcompose.ui

import com.example.naversearchcompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.naversearchcompose.ui.MainContract.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): BaseViewModel<MainState, MainSideEffect>(){
    override fun createInitialState(): MainState = MainState()

    init {

    }

    private fun load(){

    }

}