package com.example.naversearchcompose.ui.memo.mapper

import com.example.naversearchcompose.common.base.Mapper
import com.example.naversearchcompose.domain.model.Memo
import com.example.naversearchcompose.model.MemoUiModel

internal object MemoUiMapper : Mapper<Memo, MemoUiModel>{
    override fun mapToRight(from: Memo): MemoUiModel {
        return MemoUiModel(
            id = from.id,
            content = from.content,
            isRead = from.isRead
        )
    }
}