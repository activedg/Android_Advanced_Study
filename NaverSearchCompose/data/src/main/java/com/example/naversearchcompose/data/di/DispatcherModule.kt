package com.example.naversearchcompose.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {
    @Provides
    @Singleton
    @Dispatcher(NaverSearchDispatcher.IO)
    fun providesIODispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    @Dispatcher(NaverSearchDispatcher.Default)
    fun providesDefaultDispatcher() = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: NaverSearchDispatcher)

enum class NaverSearchDispatcher {
    Default,
    IO,
}