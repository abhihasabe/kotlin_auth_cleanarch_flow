package com.example.cleanarch.feature.auth.domain.usecase

import APIState
import com.example.cleanarch.core.util.Results
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest): Flow<Results<LoginResponse>> =
        authRepository.loginUser(login = loginRequest)
}