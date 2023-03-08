package com.example.naversearchtest.base

sealed interface ViewModelType{
    interface State: ViewModelType
    // View 에서 처리
    interface SideEffect: ViewModelType
    // ViewModel 에서 처리
    interface Event: ViewModelType
}