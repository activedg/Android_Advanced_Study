package com.example.naversearchcompose.domain.usecase

import com.example.naversearchcompose.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClearNewsPageConfigUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.clearPageConfig()
}