package com.merteroglu286.monolitharchitecturesample.di

import com.merteroglu286.monolitharchitecturesample.data.remote.ApiService
import com.merteroglu286.monolitharchitecturesample.data.repository.ApiRepositoryImpl
import com.merteroglu286.monolitharchitecturesample.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }
}