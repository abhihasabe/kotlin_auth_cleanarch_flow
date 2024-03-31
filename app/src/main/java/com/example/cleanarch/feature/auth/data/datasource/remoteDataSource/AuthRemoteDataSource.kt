package com.example.cleanarch.feature.auth.data.datasource.remoteDataSource

import com.example.cleanarch.core.util.Results
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.data.model.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRemoteDataSource {

    suspend fun loginUser(login: LoginRequest): Flow<Results<LoginResponse>>
    suspend fun registerUser(user: RegisterRequest): Flow<Results<RegisterResponse>>
    suspend fun forgotPassword(username: String, userToken: String): Flow<Results<String>>
}