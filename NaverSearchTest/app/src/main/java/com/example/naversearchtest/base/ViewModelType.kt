package com.example.naversearchtest.base

sealed interface ViewModelType{
    interface State: ViewModelType
    interface SideEffect: ViewModelType
    interface Event: ViewModelType
}