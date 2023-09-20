package com.example.naversearchcompose.local.di

import android.content.Context
import androidx.room.Room
import com.example.naversearchcompose.local.room.NaverSearchDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class LocalServiceModule {
    @Singleton
    @Provides
    fun provideLunchVoteDataBase(@ApplicationContext context: Context) : NaverSearchDataBase {
        return Room.databaseBuilder(context, NaverSearchDataBase::class.java, context.packageName)
            .enableMultiInstanceInvalidation()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMemoDao(naverSearchDataBase: NaverSearchDataBase) = naverSearchDataBase.memoDao
}