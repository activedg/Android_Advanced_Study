package com.example.naversearchtest.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverApiClient {
    private const val BASE_URL = "https://openapi.naver.com/v1/datalab/search"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}