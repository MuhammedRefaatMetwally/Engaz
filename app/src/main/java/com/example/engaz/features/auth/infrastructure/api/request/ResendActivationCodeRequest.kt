package com.example.engaz.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class ResendActivationCodeRequest(
    @SerializedName("emailsvg.xml") val email: String,
)
