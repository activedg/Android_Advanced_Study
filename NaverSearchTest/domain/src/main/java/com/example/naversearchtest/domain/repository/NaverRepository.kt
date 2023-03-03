package com.example.naversearchtest.domain.repository

import com.example.naversearchtest.domain.entity.NewsResult
import kotlinx.coroutines.flow.Flow

interface NaverRepository {
    fun getSearchData(keyword: String) : Flow<NewsResult>
}