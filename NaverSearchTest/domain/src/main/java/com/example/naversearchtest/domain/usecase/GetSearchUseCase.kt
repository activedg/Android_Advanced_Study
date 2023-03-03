package com.example.naversearchtest.domain.usecase

import com.example.naversearchtest.domain.repository.NaverRepository
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val naverRepository: NaverRepository
) {
    operator fun invoke(keyword: String) = naverRepository.getSearchData(keyword)
}