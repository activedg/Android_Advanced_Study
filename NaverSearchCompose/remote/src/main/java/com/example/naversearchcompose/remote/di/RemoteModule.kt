package com.example.naversearchcompose.remote.di

import com.example.naversearchcompose.data.source.remote.NewsRemoteDataSource
import com.example.naversearchcompose.remote.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteModule {
    @Binds
    @Singleton
    fun bindNewsRemoteDataSource(
        newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource
}