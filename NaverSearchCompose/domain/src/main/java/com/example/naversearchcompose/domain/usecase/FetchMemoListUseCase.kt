package com.example.naversearchcompose.domain.usecase

import com.example.naversearchcompose.domain.repository.MemoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchMemoListUseCase @Inject constructor(
    private val repository: MemoRepository
) {
    operator fun invoke() = repository.fetchList()
}