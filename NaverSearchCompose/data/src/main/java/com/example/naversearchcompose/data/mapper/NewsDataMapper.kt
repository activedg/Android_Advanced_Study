package com.example.naversearchcompose.data.mapper

import com.example.naversearchcompose.common.base.Mapper
import com.example.naversearchcompose.data.model.NewsData
import com.example.naversearchcompose.domain.model.News

internal object NewsDataMapper : Mapper<NewsData, News> {
    override fun mapToRight(from: NewsData): News {
        return News(
            description = from.description,
            link = from.link,
            originalLink = from.originalLink,
            pubDate = from.pubDate,
            title = from.title
        )
    }
}