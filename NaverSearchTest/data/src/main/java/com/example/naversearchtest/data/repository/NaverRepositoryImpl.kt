package com.example.naversearchtest.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.naversearchtest.data.api.NaverService
import com.example.naversearchtest.data.di.DispatcherModule
import com.example.naversearchtest.data.remote.NaverNewsPagingSource
import com.example.naversearchtest.domain.entity.NewsItem
import com.example.naversearchtest.domain.repository.NaverRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class NaverRepositoryImpl @Inject constructor(
    private val naverService: NaverService,
    @DispatcherModule.IoDispatcher private val dispatcher: CoroutineDispatcher,
): NaverRepository{
    override fun getSearchData(keyword: String): Flow<PagingData<NewsItem>> {
        return Pager(
            config = PagingConfig(pageSize = NEWS_PAGE_SIZE),
            pagingSourceFactory = { NaverNewsPagingSource(keyword, dispatcher, naverService)}
        ).flow
    }

    companion object{
        const val NEWS_PAGE_SIZE = 10
    }
}