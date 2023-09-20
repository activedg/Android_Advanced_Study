package com.example.naversearchcompose.local

import com.example.naversearchcompose.data.di.Dispatcher
import com.example.naversearchcompose.data.di.NaverSearchDispatcher.IO
import com.example.naversearchcompose.data.model.MemoData
import com.example.naversearchcompose.data.source.local.MemoLocalDataSource
import com.example.naversearchcompose.local.mapper.MemoLocalMapper
import com.example.naversearchcompose.local.room.dao.MemoDao
import com.example.naversearchcompose.local.room.entity.MemoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

internal class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao,
    @Dispatcher(IO) private val dispatcher: CoroutineDispatcher
): MemoLocalDataSource{
    override fun fetchList(): Flow<List<MemoData>> {
        return memoDao.fetchList().map { it.map(MemoLocalMapper::mapToRight) }.flowOn(dispatcher)
    }

    override suspend fun insertList(
        memoList: List<MemoData>
    ) = withContext(dispatcher){
        memoDao.saveAll(memoList.map(MemoLocalMapper::mapToLeft))
    }

    override suspend fun insertRandomList() = withContext(dispatcher){
        buildList {
            repeat(5){
                add(MemoEntity(content = "memo ${Random.nextInt()}"))
            }
        }.run {
            memoDao.saveAll(this)
        }
    }
}