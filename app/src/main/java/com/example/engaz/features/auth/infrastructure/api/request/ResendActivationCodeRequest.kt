package com.example.engaz.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class ResendActivationCodeRequest(
    @SerializedName("email") val email: String,
)
