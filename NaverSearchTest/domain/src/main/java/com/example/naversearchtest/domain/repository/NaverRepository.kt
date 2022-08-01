package com.example.naversearchtest.domain.repository

import com.example.naversearchtest.domain.model.SearchResponse

interface NaverRepository {
    suspend fun getSearchResult(): SearchResponse?
}