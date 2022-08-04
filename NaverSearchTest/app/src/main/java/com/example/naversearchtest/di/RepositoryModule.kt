package com.example.naversearchtest.di

import com.example.naversearchtest.data.repository.NaverRepositoryImpl
import com.example.naversearchtest.data.repository.remote.datasourceImpl.NaverDataSourceImpl
import com.example.naversearchtest.domain.repository.NaverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNaverRepository(naverDataSourceImpl: NaverDataSourceImpl) :NaverRepository{
        return NaverRepositoryImpl(naverDataSourceImpl)
    }
}