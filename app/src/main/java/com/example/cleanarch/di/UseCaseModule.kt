package com.example.cleanarch.di

import com.example.cleanarch.feature.auth.domain.repository.AuthRepository
import com.example.cleanarch.feature.auth.domain.usecase.ForgotPasswordUseCase
import com.example.cleanarch.feature.auth.domain.usecase.LoginUseCase
import com.example.cleanarch.feature.auth.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun providesRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository = authRepository)
    }

    @Provides
    @Singleton
    fun providesForgotPasswordUseCase(authRepository: AuthRepository): ForgotPasswordUseCase {
        return ForgotPasswordUseCase(authRepository = authRepository)
    }
}