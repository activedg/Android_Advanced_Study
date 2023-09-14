package com.example.naversearchcompose.remote

import com.example.naversearchcompose.data.model.NewsData
import com.example.naversearchcompose.data.model.PageConfig
import com.example.naversearchcompose.data.source.NewsRemoteDataSource
import com.example.naversearchcompose.remote.mapper.NewsListRemoteMapper
import com.example.naversearchcompose.remote.retrofit.api.NewsApi
import javax.inject.Inject

internal class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApi: NewsApi
): NewsRemoteDataSource{
    override suspend fun fetchList(
        query: String,
        start: Int,
        display: Int
    ): Pair<List<NewsData>, PageConfig> {
        return newsApi.fetchList(query, start, display)
            .let(NewsListRemoteMapper::mapToRight)
    }
}