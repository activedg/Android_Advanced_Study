package com.example.naversearchtest.data.di

import com.example.naversearchtest.data.repository.NaverRepositoryImpl
import com.example.naversearchtest.domain.repository.NaverRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNaverRepository(
        naverRepositoryImpl: NaverRepositoryImpl
    ) : NaverRepository
}