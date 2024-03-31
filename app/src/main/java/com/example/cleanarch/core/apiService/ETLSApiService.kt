package com.example.cleanarch.core.apiService

import com.example.cleanarch.core.util.BuildConfig
import com.example.cleanarch.feature.auth.data.model.LoginRequest
import com.example.cleanarch.feature.auth.data.model.LoginResponse
import com.example.cleanarch.feature.auth.data.model.RegisterRequest
import com.example.cleanarch.feature.auth.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ETLSApiService {

    //Login user
    @POST(BuildConfig.LOGIN)
    suspend fun loginUser(@Body login: LoginRequest): Response<LoginResponse>

    //Register user
    @POST(BuildConfig.REGISTER)
    suspend fun registerUser(@Body register: RegisterRequest): Response<RegisterResponse>

    @GET("/users/forget-password/{username}")
    suspend fun forgotPassword(
        @Path(value = "username") username: String, @Header("app-token") userToken: String
    ): Response<String>

    /*
    @GET("/api/products")
    suspend fun getProducts(@Header("x-auth-token") userToken: String): Response<ProductItem>

    @GET("products/{id}")
    suspend fun getProduct(@Path(value = "id") itemId : Int) : Response<ProductResponse>*/

}