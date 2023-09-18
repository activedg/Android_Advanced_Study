package com.example.naversearchcompose.data

import com.example.naversearchcompose.data.mapper.NewsDataMapper
import com.example.naversearchcompose.data.model.PageConfig
import com.example.naversearchcompose.data.source.NewsRemoteDataSource
import com.example.naversearchcompose.domain.model.News
import com.example.naversearchcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

internal class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDataSource
): NewsRepository {
    private val pageConfig = MutableStateFlow(PageConfig())

    override suspend fun fetchList(query: String): List<News> {
        if (pageConfig.value.isLast) return emptyList()

        val result = remote.fetchList(query, pageConfig.value.start, pageConfig.value.page)

        pageConfig.value = pageConfig.value.copy(
            start = pageConfig.value.start + result.first.size,
            total = result.second.total
        )

        Timber.e("pageConfig : ${pageConfig.value}")

        return result.first.map(NewsDataMapper::mapToRight)
    }

    override suspend fun clearPageConfig() {
        pageConfig.value = PageConfig()
    }
}