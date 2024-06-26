package com.example.engaz.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("username") val userName: String,
    @SerializedName("emailsvg.xml") val email: String,
    @SerializedName("password") val password: String,
)
