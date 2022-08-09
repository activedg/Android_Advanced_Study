package com.example.naversearchtest.data.remote

import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import retrofit2.Response
import retrofit2.http.*

interface NaverService {
    @POST ("/v1/datalab/search")
    suspend fun getSearchResult(
        @Body data: SearchData
    ) : Response<SearchResponse>
}