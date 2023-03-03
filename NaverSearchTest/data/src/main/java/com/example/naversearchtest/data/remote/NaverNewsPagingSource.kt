package com.example.naversearchtest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.naversearchtest.data.api.NaverService
import com.example.naversearchtest.domain.entity.NewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NaverNewsPagingSource(
    private val keyword: String,
    private val ioDispatcher: CoroutineDispatcher,
    private val naverService: NaverService
) : PagingSource<Int, NewsItem>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        return try {
            val start = if (params.key != null) params.key!! * 10 + 1 else 1
            val response = withContext(ioDispatcher){
                naverService.getSearchResult(keyword, start)
            }

//            val prevKey = if (page == 1) null else page - 1
//            val nextKey = if (bookList.isEmpty() || response.body()?.meta?.isEnd == true) null else page + 1

            val next = params.key ?: 0

            LoadResult.Page(
                data = response.items,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    // 이게 뭔데..
    override fun getRefreshKey(state: PagingState<Int, NewsItem>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}