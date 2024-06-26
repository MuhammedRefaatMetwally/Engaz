package com.example.engaz.features.auth.view.viewmodels.login

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.util.usecase.ValidateEmailLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.views.components.PhoneNumber
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RegisterScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordByPhoneScreenDestination
import com.example.engaz.features.auth.domain.usecases.LoginUseCase
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validateEmailLocalUseCase: ValidateEmailLocalUseCase,
    private val validatePasswordLocalUseCase: ValidatePasswordLocalUseCase,

    ) : ViewModel() {

    val loginScreenId = 0
    var state by mutableStateOf(LoginState())
    private var job: Job? = null


    fun updatePassword(paasword: String) {
        state = state.copy(
            password = paasword
        )
    }

    fun updateEmail(email: String) {
        state = state.copy(
            email = email
        )
    }

    fun updatePhoneWithCountryCode(number: PhoneNumber) {
        state = state.copy(
            countryCode = number.countryCode
        )
    }

    fun updatePasswordSecureState() {
        state = state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    private fun updateRememberMeState() {
        state = state.copy(
            rememberMe = !state.rememberMe
        )
    }

    private fun onForgotPasswordClick(navigator: DestinationsNavigator) {
        navigator.navigate(ResetPasswordByPhoneScreenDestination())
    }

    private fun onRegisterClick(navigator: DestinationsNavigator) {
        navigator.navigate(RegisterScreenDestination())
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        val contentResolver = context.contentResolver
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    private fun onLoginClick(navigator: DestinationsNavigator, context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isLoginLoading = true)
            val response = loginUseCase(
                LoginRequest(
                    email = state.email,
                    password = state.password,
                ),
                context,
            )
            state = state.copy(isLoginLoading = false)

            if (response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                CoreViewModel.showSnackbar(("Success:" + response.data?.message))
                viewModelScope.launch(Dispatchers.Main) {
                    navigator.popBackStack()
                    navigator.navigate(MainScreenDestination())
                }

            }

        }
    }


    private fun loginWithGoogle() {

    }

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.Login -> {
                validateForm(event.context) { onLoginClick(event.navigator, event.context) }
            }

            is LoginEvent.Register -> {
                onRegisterClick(event.navigator)
            }

            is LoginEvent.LoginWithGoogle -> {
                loginWithGoogle()
            }

            is LoginEvent.RememberMe -> {
                updateRememberMeState()
            }

            is LoginEvent.ForgotPassword -> {
                onForgotPasswordClick(event.navigator)
            }

            is LoginEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
        }
    }

    private fun validateForm(context: Context, callBackFunction: () -> Unit) {
        val phoneResult = validateEmailLocalUseCase(state.email, context)
        val passwordResult = validatePasswordLocalUseCase(state.password, context)

        val hasError = listOf(
            phoneResult,
            passwordResult
        ).any {
            it.failure != null
        }

        state = state.copy(
            emailError = phoneResult.failure?.message,
            passwordError = passwordResult.failure?.message
        )

        if (hasError) {
            return
        }

        callBackFunction()

    }


}