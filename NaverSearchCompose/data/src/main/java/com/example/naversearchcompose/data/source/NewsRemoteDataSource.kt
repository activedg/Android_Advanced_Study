package com.example.naversearchcompose.data.source

import com.example.naversearchcompose.data.model.NewsData
import com.example.naversearchcompose.data.model.PageConfig

interface NewsRemoteDataSource {
    suspend fun fetchList(
        query: String,
        start: Int,
        display: Int
    ): Pair<List<NewsData>, PageConfig>
}