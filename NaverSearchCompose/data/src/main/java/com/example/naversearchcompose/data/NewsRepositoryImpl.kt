package com.example.naversearchcompose.data

import com.example.naversearchcompose.data.mapper.NewsDataMapper
import com.example.naversearchcompose.data.model.PageConfig
import com.example.naversearchcompose.data.source.remote.NewsRemoteDataSource
import com.example.naversearchcompose.domain.model.News
import com.example.naversearchcompose.domain.repository.NewsRepository
import timber.log.Timber
import javax.inject.Inject

internal class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDataSource
): NewsRepository {
    private var pageConfig = PageConfig()

    override suspend fun fetchList(query: String): List<News> {
        if (pageConfig.isLast) return emptyList()

        val result = remote.fetchList(query, pageConfig.start, pageConfig.page)

        pageConfig = pageConfig.copy(
            start = pageConfig.start + result.first.size,
            total = result.second.total
        )

        Timber.e("pageConfig : ${pageConfig}")

        return result.first.map(NewsDataMapper::mapToRight)
    }

    override suspend fun clearPageConfig() {
        pageConfig = PageConfig()
    }
}