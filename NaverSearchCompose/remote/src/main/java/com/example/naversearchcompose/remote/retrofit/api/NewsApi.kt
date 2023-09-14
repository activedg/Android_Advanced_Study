package com.example.naversearchcompose.remote.retrofit.api

import com.example.naversearchcompose.remote.model.GetNewsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApi {
    @GET("search/news.json")
    suspend fun fetchList(
        @Query("query") query: String,
        @Query("start") start: Int,
        @Query("display") perPage: Int
    ): GetNewsListResponse
}