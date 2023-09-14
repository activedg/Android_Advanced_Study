package com.example.naversearchcompose.domain.usecase

import com.example.naversearchcompose.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchNewsListUseCase @Inject constructor(
    private val repository: NewsRepository
){
    suspend operator fun invoke(query: String) = repository.fetchList(query)
}