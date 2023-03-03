package com.example.naversearchtest.domain.repository

import androidx.paging.PagingData
import com.example.naversearchtest.domain.entity.NewsItem
import com.example.naversearchtest.domain.entity.NewsResult
import kotlinx.coroutines.flow.Flow

interface NaverRepository {
    fun getSearchData(keyword: String) : Flow<PagingData<NewsItem>>
}