package com.example.naversearchtest.domain.usecase

import com.example.naversearchtest.domain.model.SearchData
import com.example.naversearchtest.domain.model.SearchResponse
import com.example.naversearchtest.domain.repository.NaverRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val naverRepository: NaverRepository
) {
    // flow 학습하기
    suspend fun execute(body: SearchData) = naverRepository.getSearchResult(body)
}