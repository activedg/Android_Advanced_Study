package com.example.naversearchcompose.data.source.local

import com.example.naversearchcompose.data.model.MemoData
import kotlinx.coroutines.flow.Flow

interface MemoLocalDataSource {
    fun fetchList() : Flow<List<MemoData>>
    suspend fun insertList(memoList: List<MemoData>)
    suspend fun insertRandomList()
}