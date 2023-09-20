package com.example.naversearchcompose.domain.repository

import com.example.naversearchcompose.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun fetchList() : Flow<List<Memo>>
    suspend fun updateList(memoList: List<Memo>)

    suspend fun insertRandomList()
}