package com.example.naversearchtest.data.api

import com.example.naversearchtest.domain.entity.NewsResult
import retrofit2.http.*

interface NaverService {
    @GET ("/v1/search/news.json")
    suspend fun getSearchResult(
        @Query("query") keyword: String
    ) : NewsResult
}