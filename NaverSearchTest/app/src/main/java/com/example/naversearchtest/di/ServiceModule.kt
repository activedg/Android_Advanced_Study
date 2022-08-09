package com.example.naversearchtest.di

import com.example.naversearchtest.data.remote.NaverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun providesNaverService(retrofit: Retrofit): NaverService{
        return retrofit.create(NaverService::class.java)
    }
}