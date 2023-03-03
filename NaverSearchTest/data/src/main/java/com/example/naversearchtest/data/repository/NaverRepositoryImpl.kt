package com.example.naversearchtest.data.repository

import com.example.naversearchtest.data.remote.NaverRemoteDataSource
import com.example.naversearchtest.domain.entity.NewsResult
import com.example.naversearchtest.domain.repository.NaverRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val naverRemoteDataSource: NaverRemoteDataSource
): NaverRepository{
    override fun getSearchData(keyword: String): Flow<NewsResult> {
        return naverRemoteDataSource.getSearchData(keyword)
    }
}