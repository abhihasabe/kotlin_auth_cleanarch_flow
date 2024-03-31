package com.example.cleanarch.di

import android.app.Application
import com.example.cleanarch.core.util.SharedPreferences
import com.example.cleanarch.feature.auth.domain.usecase.ForgotPasswordUseCase
import com.example.cleanarch.feature.auth.domain.usecase.LoginUseCase
import com.example.cleanarch.feature.auth.domain.usecase.RegisterUseCase
import com.example.cleanarch.feature.auth.presenation.viewmodel.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    @Singleton
    fun providesLoginViewModel(
        authUseCase: LoginUseCase,
        registerUseCase: RegisterUseCase,
        forgotPasswordUseCase: ForgotPasswordUseCase,
        sharedPreference: SharedPreferences,
        applicationsContext: Application
    ): AuthViewModel {
        return AuthViewModel(
            authUseCase,
            registerUseCase,
            forgotPasswordUseCase,
            sharedPreference,
            applicationsContext
        )
    }

}