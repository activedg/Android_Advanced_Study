package com.example.naversearchcompose.local.di

import com.example.naversearchcompose.data.source.local.MemoLocalDataSource
import com.example.naversearchcompose.local.MemoLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LocalModule {
    @Binds
    @Singleton
    fun bindMemoLocalDataSource(
        memoLocalDataSourceImpl: MemoLocalDataSourceImpl
    ): MemoLocalDataSource
}