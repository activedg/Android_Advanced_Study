package com.example.naversearchcompose.ui.memo

import androidx.lifecycle.SavedStateHandle
import com.example.naversearchcompose.base.BaseViewModel
import com.example.naversearchcompose.domain.model.Memo
import com.example.naversearchcompose.domain.usecase.FetchMemoListUseCase
import com.example.naversearchcompose.domain.usecase.InsertRandomMemoListUseCase
import com.example.naversearchcompose.domain.usecase.UpdateMemoReadUseCase
import com.example.naversearchcompose.ui.memo.MemoContract.*
import com.example.naversearchcompose.ui.memo.mapper.MemoUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val fetchMemoListUseCase: FetchMemoListUseCase,
    private val insertRandomMemoListUseCase: InsertRandomMemoListUseCase,
    private val updateMemoReadUseCase: UpdateMemoReadUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel<MemoState, MemoSideEffect>(savedStateHandle) {
    override fun createInitialState(): MemoState = MemoState()

    private val _memoList = MutableStateFlow<List<Memo>>(emptyList())
    init {
        fetchList()
    }

    private fun fetchList() = intent {
        launch {
            fetchMemoListUseCase()
                .collectLatest {
                    Timber.e("memo", it.toString())
                    reduce {
                        _memoList.value = it
                        state.copy(memoList = it.map(MemoUiMapper::mapToRight))
                    }
                }
        }
    }

    fun addMemos() = intent {
        launch {
            insertRandomMemoListUseCase()
        }
    }

    override fun onCleared() {
        // todo : 화면에 들어왔을 때 업데이트 갱신, 현재는 cleared일때 읽음 처리

        CoroutineScope(Dispatchers.IO).launch{
            updateMemoReadUseCase(_memoList.value)
        }
        super.onCleared()
    }
}