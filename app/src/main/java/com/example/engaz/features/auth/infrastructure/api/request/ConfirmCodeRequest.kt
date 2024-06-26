package com.example.engaz.features.auth.infrastructure.api.request

import com.google.gson.annotations.SerializedName

data class ConfirmCodeRequest(
    @SerializedName("email") val email: String,
    @SerializedName("otpCode") val otpCode: String,

)
