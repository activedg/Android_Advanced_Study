package com.example.naversearchtest.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NaverApi {
    @GET("/{type}")
    suspend fun getSearchResult(
        @Header("X-Naver-Client-Id") id: String,
        @Header("X-Naver_Client-Secret") pw: String,
        @Path("type") type: String,
        @Query("query") query: String
    )
}