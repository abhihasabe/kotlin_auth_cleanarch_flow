package com.example.cleanarch.di

import com.example.cleanarch.feature.auth.data.datasource.localDataSource.AuthLocalDataSource
import com.example.cleanarch.feature.auth.data.datasource.remoteDataSource.AuthRemoteDataSource
import com.example.cleanarch.feature.auth.data.repository.AuthRepositoryImpl
import com.example.cleanarch.feature.auth.domain.repository.AuthRepository
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
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(
            authRemoteDataSource = authRemoteDataSource
        )
    }
}