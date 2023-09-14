package com.example.naversearchcompose.base

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<STATE: Any, SIDE_EFFECT: Any> : ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    private val initialState by lazy {
        createInitialState()
    }
    protected abstract fun createInitialState(): STATE

    override val container = container<STATE, SIDE_EFFECT>(initialState)

    val state : StateFlow<STATE> = container.stateFlow
    val sideEffect : Flow<SIDE_EFFECT> = container.sideEffectFlow

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error.asSharedFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _toast : MutableSharedFlow<String> = MutableSharedFlow()
    val toast : SharedFlow<String> = _toast.asSharedFlow()


    val ceh = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        setLoading(false)
        setError(throwable.message ?: "")
    }

    inline fun launch(crossinline action: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(ceh) {
            action(this)
        }
    }

    protected fun setError(errorMsg: String) = viewModelScope.launch {
        _error.emit(errorMsg)
    }

    protected fun setLoading(isVisible: Boolean) = viewModelScope.launch {
        _isLoading.emit(isVisible)
    }

    protected fun sendToast(msg : String) = viewModelScope.launch {
        _toast.emit(msg)
    }

    protected inline fun launchWithLoading(crossinline job: suspend () -> Unit) {
        launch {
            setLoading(true)
            job.invoke()
            setLoading(false)
        }
    }

}