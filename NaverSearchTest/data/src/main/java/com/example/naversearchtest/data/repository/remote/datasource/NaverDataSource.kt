package com.example.naversearchtest.data.repository.remote.datasource

import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import retrofit2.Response

interface NaverDataSource {
    suspend fun getSearch(body: SearchData) : SearchResponse?
}