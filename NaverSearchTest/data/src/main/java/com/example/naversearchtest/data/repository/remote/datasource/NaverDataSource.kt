package com.example.naversearchtest.data.repository.remote.datasource

interface NaverDataSource {
    suspend fun getSearch(type: String)
}