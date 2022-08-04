package com.example.naversearchtest.data.repository

import com.example.naversearchtest.data.repository.remote.datasource.NaverDataSource
import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import com.example.naversearchtest.domain.repository.NaverRepository
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val naverDataSource: NaverDataSource
): NaverRepository{
    override suspend fun getSearchResult(searchData: SearchData): SearchResponse? {
        // mapper 통해 구현 가능
        return naverDataSource.getSearch(searchData)
    }
}