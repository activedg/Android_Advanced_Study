package com.example.naversearchcompose.ui.memo

import android.os.Parcelable
import com.example.naversearchcompose.model.MemoUiModel
import kotlinx.parcelize.Parcelize

class MemoContract {
    @Parcelize
    data class MemoState(
        val memoList: List<MemoUiModel> = emptyList()
    ): Parcelable

    sealed interface MemoSideEffect {

    }
}