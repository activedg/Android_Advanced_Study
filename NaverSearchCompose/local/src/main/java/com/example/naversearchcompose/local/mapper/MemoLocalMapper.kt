package com.example.naversearchcompose.local.mapper

import com.example.naversearchcompose.common.base.BiMapper
import com.example.naversearchcompose.data.model.MemoData
import com.example.naversearchcompose.local.room.entity.MemoEntity

internal object MemoLocalMapper : BiMapper<MemoEntity, MemoData> {
    override fun mapToRight(from: MemoEntity): MemoData {
        return MemoData(
            id = from.id,
            content = from.content,
            isRead = from.isRead
        )
    }

    override fun mapToLeft(from: MemoData): MemoEntity {
        return MemoEntity(
            id = from.id,
            content = from.content,
            isRead = true
        )
    }
}