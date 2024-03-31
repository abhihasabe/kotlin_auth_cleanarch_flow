package com.example.cleanarch.feature.auth.data.model

data class RegisterResponse(
    val dateOfBirth: Any,
    val email: String,
    val firstName: String,
    val gender: String,
    val isActive: Boolean,
    val isProfileDisabled: Boolean,
    val lastName: String,
    val mobileNumber: String,
    val profileId: String,
    val profileImageUrl: String,
    val token: String,
    val userId: Int
)