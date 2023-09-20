package com.example.naversearchcompose.data.mapper

import com.example.naversearchcompose.common.base.BiMapper
import com.example.naversearchcompose.data.model.MemoData
import com.example.naversearchcompose.domain.model.Memo

internal object MemoDataMapper : BiMapper<MemoData, Memo> {
    override fun mapToRight(from: MemoData): Memo {
        return Memo(
            id = from.id,
            content = from.content,
            isRead = from.isRead
        )
    }

    override fun mapToLeft(from: Memo): MemoData {
        return MemoData(
            id = from.id,
            content = from.content,
            isRead = from.isRead
        )
    }
}