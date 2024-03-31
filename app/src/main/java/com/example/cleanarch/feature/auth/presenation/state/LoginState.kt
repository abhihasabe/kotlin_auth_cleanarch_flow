package com.example.cleanarch.feature.auth.presenation.state

import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.data.model.RegisterResponse


sealed class LoginState<out R> {
    data class Loading(val isLoading: Boolean) : LoginState<Boolean>()
    data class Error(val message: String) : LoginState<String>()
    data class Success(val data: LoginResponse) : LoginState<LoginResponse>()
}

sealed class RegisterState<out R> {
    data class Loading(val isLoading: Boolean) : RegisterState<Boolean>()
    data class Error(val message: String) : RegisterState<String>()
    data class Success(val data: RegisterResponse) : RegisterState<LoginResponse>()
}

sealed class ForgotPasswordState<out R> {
    data class Loading(val isLoading: Boolean) : ForgotPasswordState<Boolean>()
    data class Error(val message: String) : ForgotPasswordState<String>()
    data class Success(val data: String) : ForgotPasswordState<LoginResponse>()
}