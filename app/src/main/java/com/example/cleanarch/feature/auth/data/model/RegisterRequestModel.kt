package com.example.cleanarch.feature.auth.data.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobileNumber") val mobileNumber: String,
    @SerializedName("password") val password: String
)
