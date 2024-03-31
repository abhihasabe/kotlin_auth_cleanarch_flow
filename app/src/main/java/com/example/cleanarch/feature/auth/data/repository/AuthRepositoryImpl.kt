package com.example.cleanarch.feature.auth.data.repository

import APIState
import com.example.cleanarch.core.util.Results
import com.example.cleanarch.feature.auth.data.datasource.remoteDataSource.AuthRemoteDataSource
import com.example.cleanarch.feature.auth.domain.repository.AuthRepository
import com.example.cleanarch.feature.auth.data.model.RegisterResponse
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun loginUser(login: LoginRequest): Flow<Results<LoginResponse>> {
        return authRemoteDataSource.loginUser(login)
    }

    override suspend fun registerUser(user: RegisterRequest): Flow<Results<RegisterResponse>> {
        return authRemoteDataSource.registerUser(user)
    }

    override suspend fun forgotPassword(
        username: String, userToken: String
    ): Flow<Results<String>> {
        return authRemoteDataSource.forgotPassword(username, userToken)
    }
}