package com.example.naversearchtest.data.repository.remote.datasourceImpl

import android.util.Log
import com.example.naversearchtest.data.base.BaseRepository
import com.example.naversearchtest.data.remote.NaverApi
import com.example.naversearchtest.data.repository.remote.datasource.NaverDataSource
import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class NaverDataSourceImpl @Inject constructor(
    private val naverApi: NaverApi
) : BaseRepository(), NaverDataSource{
    override suspend fun getSearch(body: SearchData): SearchResponse? {
        // parameter 추가
       return naverApi.getSearchResult(body).also { Log.e("response", it.toString()) }.body()
    }
}