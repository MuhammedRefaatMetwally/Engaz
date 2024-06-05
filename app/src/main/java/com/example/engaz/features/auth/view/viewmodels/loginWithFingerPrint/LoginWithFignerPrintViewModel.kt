package com.example.engaz.features.auth.view.viewmodels.loginWithFingerPrint

import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePhoneLocalUseCase
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.views.components.PhoneNumber
import com.example.engaz.features.auth.domain.usecases.LoginUseCase
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RegisterScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordByPhoneScreenDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginWithFignerPrintViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatePhoneLocalUseCase: ValidatePhoneLocalUseCase,
    private val validatePasswordLocalUseCase: ValidatePasswordLocalUseCase,

    ) : ViewModel() {

    val loginScreenId = 0
    var state by mutableStateOf(LoginWithFignerPrintState())
    private var job : Job? = null


    fun updatePassword(paasword : String){
        state = state.copy(
            password = paasword
        )
    }

    fun updatePhone(phone : String){
        state = state.copy(
            emailOrPassCode = phone
        )
    }

    fun updatePhoneWithCountryCode(number: PhoneNumber) {
        state = state.copy(
            countryCode = number.countryCode
        )
    }

    fun updatePasswordSecureState(){
        state = state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    private fun updateRememberMeState(){
        state = state.copy(
            rememberMe = !state.rememberMe
        )
    }

    private fun onForgotPasswordClick(navigator: DestinationsNavigator){
        navigator.navigate(ResetPasswordByPhoneScreenDestination())
    }

    private fun onRegisterClick(navigator: DestinationsNavigator){
        navigator.navigate(RegisterScreenDestination())
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    fun getDeviceId(context: Context): String {
        val contentResolver = context.contentResolver
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    private fun onLoginClick(navigator: DestinationsNavigator,context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isLoginLoading = true)
            val response = loginUseCase(
                LoginRequest(
                    phone = state.emailOrPassCode,
                    countryCode = state.countryCode,
                    password = state.password,
                    deviceToken = getDeviceId(context),
                    deviceType = "android",
                ),
                context,
            )
            state = state.copy(isLoginLoading = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                CoreViewModel.showSnackbar(("Success:" + response.data?.message))
                viewModelScope.launch(Dispatchers.Main){
                    navigator.navigate(MainScreenDestination())
                }

            }

        }
    }




    private fun loginWithGoogle(){

    }

    fun onEvent(event : LoginWithFignerPrintEvent){

        when(event){
            is LoginWithFignerPrintEvent.Login -> {
                validateForm(event.context) { onLoginClick(event.navigator,event.context) }
            }
            is LoginWithFignerPrintEvent.Register -> {
                onRegisterClick(event.navigator)
            }
            is LoginWithFignerPrintEvent.LoginWithGoogle -> {
                loginWithGoogle()
            }
            is LoginWithFignerPrintEvent.RememberMe -> {
                updateRememberMeState()
            }
            is LoginWithFignerPrintEvent.ForgotPassword -> {
                onForgotPasswordClick(event.navigator)
            }

            is LoginWithFignerPrintEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
        }
    }

    private fun validateForm(context: Context, callBackFunction : ()-> Unit){
        val phoneResult = validatePhoneLocalUseCase(state.emailOrPassCode,context)
        val passwordResult = validatePasswordLocalUseCase(state.password,context)

        val hasError = listOf(
            phoneResult,
            passwordResult
        ).any {
            it.failure != null
        }

        state = state.copy(
            emailOrPassCodeError = phoneResult.failure?.message,
            passwordError = passwordResult.failure?.message
        )

        if(hasError) {
            return
        }

        callBackFunction()

    }



}