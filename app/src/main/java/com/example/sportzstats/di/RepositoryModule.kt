package com.example.sportzstats.di

import com.example.sportzstats.data.remote.PostApiService
import com.example.sportzstats.data.repository.PostRepositoryImpl
import com.example.sportzstats.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePostRepository(postApiService: PostApiService): PostRepository {
        return PostRepositoryImpl(postApiService)
    }
}