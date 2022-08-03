package com.example.naversearchtest.data.repository.remote.datasourceImpl

import com.example.naversearchtest.data.base.BaseRepository
import com.example.naversearchtest.data.remote.NaverApi
import com.example.naversearchtest.data.repository.remote.datasource.NaverDataSource
import com.example.naversearchtest.domain.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class NaverDataSourceImpl @Inject constructor(
    private val naverApi: NaverApi
) : BaseRepository(), NaverDataSource{
    override suspend fun getSearch(type: String): SearchResponse? {
        // parameter 추가
//        return safeApiCall { naverApi.getSearchResult(type).body() }
        return null
    }
}