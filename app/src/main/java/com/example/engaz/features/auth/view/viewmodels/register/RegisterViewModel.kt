package com.example.engaz.features.auth.view.viewmodels.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.util.usecase.ValidateNationalIDLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePasswordRepeatedLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePhoneLocalUseCase
import com.example.engaz.core.util.usecase.ValidateUsernameLocalUseCase
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.views.components.PhoneNumber
import io.github.raamcosta.compose_destinations.destinations.ActivationPinScreenDestination
import io.github.raamcosta.compose_destinations.destinations.LoginScreenDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import com.example.engaz.features.auth.domain.usecases.ConfirmCodeUseCase
import com.example.engaz.features.auth.domain.usecases.RegisterUseCase
import com.example.engaz.features.auth.domain.usecases.ResendActivitionCodeUseCase
import com.example.engaz.features.auth.infrastructure.api.request.ConfirmCodeRequest
import com.example.engaz.features.auth.infrastructure.api.request.RegisterRequest
import com.example.engaz.features.auth.infrastructure.api.request.ResendActivitionCodeRequest
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val resendActivitionCodeUseCase: ResendActivitionCodeUseCase,
    private val confirmCodeUseCase: ConfirmCodeUseCase,
    private val validateUsernameLocalUseCase: ValidateUsernameLocalUseCase,
    private val validateNationalIDLocalUseCase : ValidateNationalIDLocalUseCase,
    private val validatePhoneLocalUseCase: ValidatePhoneLocalUseCase,
    private val validatePasswordLocalUseCase: ValidatePasswordLocalUseCase,
    private val validatePasswordRepeatedLocalUseCase: ValidatePasswordRepeatedLocalUseCase,


    ) : ViewModel() {

    var state by mutableStateOf(RegisterState())
    private var job : Job? = null

    fun updateFullName(fullName: String) {
        state = state.copy(
            fullName = fullName
        )
    }


    fun updatePhone(phone: String, context: Context) {
        state = state.copy(
            phone = phone
        )
    }

    fun updatePhoneWithCountryCode(phoneNumber: PhoneNumber) {
        state = state.copy(
            countryCode = phoneNumber.countryCode,
        )
    }

    fun updatePassword(password: String) {
        state = state.copy(
            password = password
        )
    }
    fun updatePassCOde(passCode:String){
        state = state.copy(
            passCode = passCode
        )
    }

    fun updatePasswordRenter(passwordRenter: String) {
        state = state.copy(
            passwordRenter = passwordRenter
        )
    }

    fun updateIsPasswordSecure() {
        state = state.copy(
            isPasswordSecure = !state.isPasswordSecure
        )
    }

    fun updateIsPasswordRenterSecure() {
        state = state.copy(
            isPasswordRenterSecure = !state.isPasswordRenterSecure
        )
    }

    fun updateTerms() {
        state = state.copy(
            terms = !state.terms
        )
    }

    fun updatePin(pinCode: String) {
        state = state.copy(
            pinCode = pinCode
        )
    }


    private fun register(navigator: DestinationsNavigator,context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isRegisterLoading = true)
            val response = registerUseCase(
                RegisterRequest(
                    fullName = state.fullName,
                    countryCode = state.countryCode,
                    phone = state.phone,
                    password = state.password,
                    confirmPassword = state.passwordRenter,
                    provider = "",
                    providerId = "",
                    terms = if(state.terms) "1" else "0",
                ),
                context = context,
            )
            state = state.copy(isRegisterLoading = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message.trim()))
            } else {
                CoreViewModel.showSnackbar(("Success:" + (response.data?.message ?: "")))
                viewModelScope.launch(Dispatchers.Main) {
                    navigator.navigate(ActivationPinScreenDestination)
                }
            }

        }
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    private fun onLoginClick(navigator: DestinationsNavigator) {
        navigator.navigate(LoginScreenDestination())
    }


    fun onEvent(event: RegisterEvent) {

        when (event) {
            is RegisterEvent.Register -> {
                validateForm(event.context, callBackFunction = { register(event.navigator,event.context) })
            }

            is RegisterEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }

            is RegisterEvent.OnLoginClick -> {
                onLoginClick(event.navigator)
            }

            is RegisterEvent.OnValidateClick -> {
                onValidateClick(event.navigator,event.context)
            }

            is RegisterEvent.OnResendClick -> {
                onResendCodeClick(event.navigator,event.context)
            }

        }

    }

    private fun onResendCodeClick(navigator: DestinationsNavigator,context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isResendingPinCode = true)
            val response = resendActivitionCodeUseCase(
                ResendActivitionCodeRequest(
                    phone = state.phone,
                    countryCode = state.countryCode
                ),
                context = context
            )
            state = state.copy(isResendingPinCode = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message.trim()))
            } else {
                CoreViewModel.showSnackbar(("Success:" + (response.data?.message ?: "")))
            }

        }
    }

    private fun onValidateClick(navigator: DestinationsNavigator,context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            state = state.copy(isValidatingPinCode = true)
            val response = confirmCodeUseCase(
                ConfirmCodeRequest(
                    phone = state.phone,
                    countryCode = state.countryCode,
                    otp = state.pinCode,
                    type = "android",
                    deviceToken = "ssssssssssss"
                ),
                context = context
            )
            state = state.copy(isValidatingPinCode = false)

            if(response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message.trim()))
            } else {
                CoreViewModel.showSnackbar(("Success:" + (response.data?.message ?: "")))
                viewModelScope.launch(Dispatchers.Main) {
                    navigator.navigate(MainScreenDestination)
                }
            }

        }
    }

    private fun validateForm(context: Context, callBackFunction: () -> Unit) {

        val fullNameResult = validateUsernameLocalUseCase(state.fullName, context)
        val phoneResult = validatePhoneLocalUseCase(state.countryCode + state.phone, context)
        val passCode = validateNationalIDLocalUseCase(state.passCode,context)
        val passwordResult = validatePasswordLocalUseCase(state.password, context)
        val passwordRepeatedResult = validatePasswordRepeatedLocalUseCase(state.passwordRenter, state.password, context)


        val hasError = listOf(
            fullNameResult,
            phoneResult,
            passwordResult,
            passCode,
            passwordRepeatedResult
        ).any {
            it.failure != null
        }

        state = state.copy(
            fullNameError = fullNameResult.failure?.message,
            phoneError = phoneResult.failure?.message,
            passwordError = passwordResult.failure?.message,
            passCodeError = passCode.failure?.message ?:"",
            passwordRenterError = passwordRepeatedResult.failure?.message
        )

        if (hasError) {
            return
        }

        callBackFunction()

    }




}