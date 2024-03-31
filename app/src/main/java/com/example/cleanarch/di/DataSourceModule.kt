package com.example.cleanarch.di

import com.example.cleanarch.feature.auth.data.datasource.remoteDataSource.AuthRemoteDataSource
import com.example.cleanarch.feature.auth.data.datasource.remoteDataSource.AuthRemoteDataSourceImpl
import com.example.cleanarch.core.apiService.ETLSApiService
import com.example.cleanarch.core.apiService.ETLSRemoteDataSource
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideETLRemoteDataSource(
        eTLSApiService: ETLSApiService, networkBoundResources: ETLSRemoteDataSource
    ): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(
            eTLSApiService = eTLSApiService, networkBoundResources = networkBoundResources
        )
    }
}