package com.example.cleanarch.feature.auth.data.datasource.remoteDataSource

import com.example.cleanarch.core.apiService.ETLSApiService
import com.example.cleanarch.core.apiService.ETLSRemoteDataSource
import com.example.cleanarch.core.util.Results
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.data.model.RegisterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val eTLSApiService: ETLSApiService,
    private val networkBoundResources: ETLSRemoteDataSource
) : AuthRemoteDataSource {
    override suspend fun loginUser(login: LoginRequest): Flow<Results<LoginResponse>> {
        return networkBoundResources.downloadData {
            eTLSApiService.loginUser(login = login)
        }
    }

    override suspend fun registerUser(user: RegisterRequest): Flow<Results<RegisterResponse>> {
        return networkBoundResources.downloadData {
            eTLSApiService.registerUser(register = user)
        }
    }

    override suspend fun forgotPassword(
        username: String, userToken: String
    ): Flow<Results<String>> {
        return networkBoundResources.downloadData {
            eTLSApiService.forgotPassword(username = username, userToken = userToken)
        }
    }
}