package com.example.naversearchcompose.remote.mapper

import com.example.naversearchcompose.common.base.Mapper
import com.example.naversearchcompose.data.model.NewsData
import com.example.naversearchcompose.data.model.PageConfig
import com.example.naversearchcompose.remote.model.GetNewsListResponse

internal object NewsListRemoteMapper : Mapper<GetNewsListResponse, Pair<List<NewsData>, PageConfig>>{
    override fun mapToRight(from: GetNewsListResponse): Pair<List<NewsData>, PageConfig> {
        return from.items.map {
            NewsData(
                title = it.title,
                originalLink = it.originalLink,
                link = it.link,
                description = it.description,
                pubDate = it.pubDate
            )
        } to PageConfig(
            page = from.display,
            start = from.start,
            total = from.total
        )
    }
}