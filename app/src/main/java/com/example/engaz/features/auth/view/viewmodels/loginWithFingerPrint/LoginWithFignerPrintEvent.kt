package com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class LoginWithFignerPrintEvent {
    data class Login(val navigator: DestinationsNavigator,val context: Context) : LoginWithFignerPrintEvent()
    object LoginWithGoogle : LoginWithFignerPrintEvent()
    data class ForgotPassword(val navigator: DestinationsNavigator) : LoginWithFignerPrintEvent()
    data class Register(val navigator: DestinationsNavigator) : LoginWithFignerPrintEvent()
    object RememberMe : LoginWithFignerPrintEvent()

    data class OnBackClick(val navigator: DestinationsNavigator) : LoginWithFignerPrintEvent()

}
