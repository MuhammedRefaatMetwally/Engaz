package com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint

data class LoginWithFignerPrintState(
    var rememberMe: Boolean = true,
    var isLoginLoading: Boolean = false,
    var isLoginWithGoogleLoading: Boolean = false,
    var isPasswordSecure: Boolean = true,

    var countryCode: String = "",

    var email: String = "",
    var emailError : String? = null,

    var password: String = "",
    val passwordError : String? = null
)
