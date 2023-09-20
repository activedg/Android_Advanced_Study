package com.example.naversearchcompose.data

import com.example.naversearchcompose.data.mapper.MemoDataMapper
import com.example.naversearchcompose.data.source.local.MemoLocalDataSource
import com.example.naversearchcompose.domain.model.Memo
import com.example.naversearchcompose.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MemoRepositoryImpl @Inject constructor(
    private val local: MemoLocalDataSource
): MemoRepository {
    override fun fetchList(): Flow<List<Memo>> {
        return local.fetchList().map { it.map(MemoDataMapper::mapToRight) }
    }

    override suspend fun updateList(memoList: List<Memo>) {
        return local.insertList(memoList.map(MemoDataMapper::mapToLeft))
    }

    override suspend fun insertRandomList() {
        return local.insertRandomList()
    }
}