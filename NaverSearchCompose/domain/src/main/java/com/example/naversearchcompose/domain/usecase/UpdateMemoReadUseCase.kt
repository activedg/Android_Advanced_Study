package com.example.naversearchcompose.domain.usecase

import com.example.naversearchcompose.domain.model.Memo
import com.example.naversearchcompose.domain.repository.MemoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateMemoReadUseCase @Inject constructor(
    private val repository: MemoRepository
){
    suspend operator fun invoke(memoList: List<Memo>) = repository.updateList(memoList)
}