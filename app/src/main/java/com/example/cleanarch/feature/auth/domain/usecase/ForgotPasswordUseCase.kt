package com.example.cleanarch.feature.auth.domain.usecase

import APIState
import com.example.cleanarch.core.util.Results
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.data.model.RegisterResponse
import com.example.cleanarch.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(username: String, userToken: String): Flow<Results<String>> =
        authRepository.forgotPassword(username = username, userToken = userToken)
}