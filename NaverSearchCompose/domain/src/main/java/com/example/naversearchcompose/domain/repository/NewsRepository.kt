package com.example.naversearchcompose.domain.repository

import com.example.naversearchcompose.domain.model.News

interface NewsRepository {
    suspend fun fetchList(
        query: String
    ): List<News>

}