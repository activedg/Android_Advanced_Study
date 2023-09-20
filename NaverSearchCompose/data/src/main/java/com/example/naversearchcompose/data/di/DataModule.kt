package com.example.naversearchcompose.data.di

import com.example.naversearchcompose.data.MemoRepositoryImpl
import com.example.naversearchcompose.data.NewsRepositoryImpl
import com.example.naversearchcompose.domain.repository.MemoRepository
import com.example.naversearchcompose.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    @Singleton
    fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository

    @Binds
    @Singleton
    fun bindMemoRepository(
        memoRepositoryImpl: MemoRepositoryImpl
    ): MemoRepository
}