package com.example.cleanarch.feature.auth.presenation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.core.util.InternetConnection.isNetworkAvailable
import com.example.cleanarch.core.util.Results
import com.example.cleanarch.core.util.SharedPreferences
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.domain.usecase.ForgotPasswordUseCase
import com.example.cleanarch.feature.auth.domain.usecase.LoginUseCase
import com.example.cleanarch.feature.auth.domain.usecase.RegisterUseCase
import com.example.cleanarch.feature.auth.presenation.state.ForgotPasswordState
import com.example.cleanarch.feature.auth.presenation.state.LoginState
import com.example.cleanarch.feature.auth.presenation.state.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val sharedPreference: SharedPreferences,
    private val app: Application
) : AndroidViewModel(app) {
    private val _loginResponse =
        MutableStateFlow<LoginState<Any>>(LoginState.Loading(isLoading = false))
    val loginStateFlow: StateFlow<LoginState<Any>>
        get() = _loginResponse

    private val _registerResponse =
        MutableStateFlow<RegisterState<Any>>(RegisterState.Loading(isLoading = false))
    val registerStateFlow: StateFlow<RegisterState<Any>>
        get() = _registerResponse


    private val _forgotPasswordResponse =
        MutableStateFlow<ForgotPasswordState<Any>>(ForgotPasswordState.Loading(isLoading = false))
    val forgotPasswordStateFlow: StateFlow<ForgotPasswordState<Any>>
        get() = _forgotPasswordResponse

    fun login(loginRequest: LoginRequest) {
        if (isNetworkAvailable(app)) {
            viewModelScope.launch {
                loginUseCase.invoke(loginRequest = loginRequest).collectLatest {
                    when (it) {
                        is Results.Success -> {
                            _loginResponse.value = LoginState.Success(data = it.data)
                            sharedPreference.saveToken(it.data.token)
                        }

                        is Results.Error -> _loginResponse.value =
                            LoginState.Error(message = it.message)

                        is Results.Loading -> _loginResponse.value =
                            LoginState.Loading(isLoading = true)
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _loginResponse.value = LoginState.Error(message = "Internet not available")
            }
        }
    }


    fun register(registerRequest: RegisterRequest) {
        if (isNetworkAvailable(app)) {
            viewModelScope.launch {
                registerUseCase.invoke(registerRequest = registerRequest).collect {
                    when (it) {
                        is Results.Success -> {
                            _registerResponse.value = RegisterState.Success(data = it.data)
                            sharedPreference.saveToken(it.data.token)
                        }

                        is Results.Error -> _registerResponse.value =
                            RegisterState.Error(message = it.message)

                        is Results.Loading -> _registerResponse.value =
                            RegisterState.Loading(isLoading = true)
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _registerResponse.value = RegisterState.Error(message = "Internet not available")
            }
        }
    }

    fun forgotPassword(userName: String) {
        if (isNetworkAvailable(app)) {
            viewModelScope.launch {
                val userToken = sharedPreference.getToken()
                Log.i("userToken:","$userToken")
                forgotPasswordUseCase.invoke(username = userName, userToken = userToken).collect {
                    when (it) {
                        is Results.Success -> _forgotPasswordResponse.value =
                            ForgotPasswordState.Success(data = it.data)

                        is Results.Error -> _forgotPasswordResponse.value =
                            ForgotPasswordState.Error(message = it.message)

                        is Results.Loading -> _forgotPasswordResponse.value =
                            ForgotPasswordState.Loading(isLoading = true)
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _forgotPasswordResponse.value =
                    ForgotPasswordState.Error(message = "Internet not available")
            }
        }
    }

}