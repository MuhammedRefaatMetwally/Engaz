package com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint

data class LoginWithFignerPrintState(
    var rememberMe: Boolean = true,
    var isLoginLoading: Boolean = false,
    var isLoginWithGoogleLoading: Boolean = false,
    var isPasswordSecure: Boolean = true,

    var countryCode: String = "",

    var emailOrPassCode: String = "",
    var emailOrPassCodeError : String? = null,

    var password: String = "",
    val passwordError : String? = null
)
