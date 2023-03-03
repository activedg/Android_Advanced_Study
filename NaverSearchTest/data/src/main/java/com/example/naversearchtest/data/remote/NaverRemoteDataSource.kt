package com.example.naversearchtest.data.remote

import com.example.naversearchtest.data.api.NaverService
import com.example.naversearchtest.data.di.DispatcherModule.IoDispatcher
import com.example.naversearchtest.domain.entity.NewsResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NaverRemoteDataSource @Inject constructor(
    private val naverService: NaverService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    fun getSearchData(keyword: String): Flow<NewsResult> = flow {
        emit(naverService.getSearchResult(keyword))
    }.flowOn(dispatcher)
}