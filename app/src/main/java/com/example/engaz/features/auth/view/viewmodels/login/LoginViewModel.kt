package com.example.engaz.features.auth.view.viewmodels.login

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.R
import com.example.engaz.core.util.usecase.ValidateEmailLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePasswordLocalUseCase
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.viewmodel.UserPreferences
import com.example.engaz.core.views.components.PhoneNumber
import com.example.engaz.features.auth.data.entities.cars.CarsResponse
import com.example.engaz.features.auth.data.entities.sendTransaction.SendTransactionRequest
import com.example.engaz.features.auth.data.entities.transferOwnerShip.TransferOwnerShipRequest
import com.example.engaz.features.auth.domain.usecases.ConfirmTransactionUseCase
import com.example.engaz.features.auth.domain.usecases.GetCarsUseCase
import com.example.engaz.features.auth.domain.usecases.GetTransactionUseCase
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RegisterScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ResetPasswordByPhoneScreenDestination
import com.example.engaz.features.auth.domain.usecases.LoginUseCase
import com.example.engaz.features.auth.domain.usecases.SearchAboutCarUseCase
import com.example.engaz.features.auth.domain.usecases.SendTransactionUseCase
import com.example.engaz.features.auth.domain.usecases.TransferOwnerShipUseCase
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
    private val searchAboutCarUseCase: SearchAboutCarUseCase,
    private val confirmTransactionUseCase : ConfirmTransactionUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
    private val sendTransactionUseCase: SendTransactionUseCase,
    private val transferOwnerShipUseCase: TransferOwnerShipUseCase,
    private val getCarsUseCase: GetCarsUseCase,

    ) : ViewModel() {
        var listOfCars = mutableListOf<CarsResponse>()
    val loginScreenId = 0
    var state by mutableStateOf(LoginState())
    private var job: Job? = null

    val carDetailsList = listOf(
        mapOf(
            "nationalId" to "عمر أحمد",
            "plateNumber" to "و ب ث 9 5 6",
            "color" to "أخضر",
            "location" to "منصورة",
            "image" to R.drawable.cars
        ),

        mapOf(
            "nationalId" to "محمد محمود ",
            "plateNumber" to "ق ع ف 3 5 7",
            "color" to "أسود",
            "location" to "طنطا",
            "image" to R.drawable.car2
        ),

        mapOf(
            "nationalId" to "موسى القصبي",
            "plateNumber" to "ب د ر 5 9 8",
            "color" to "ازرق",
            "location" to "القاهرة",
            "image" to R.drawable.blue_car
        ),

        mapOf(
            "nationalId" to "أمير المحلاوي",
            "plateNumber" to "ي و ت 5 4 3",
            "color" to "أسود",
            "location" to "منصورة",
            "image" to R.drawable.brown_car
        ),

        mapOf(
            "nationalId" to "محمد مصطفى",
            "plateNumber" to "ط  أ ك 4 2 9",
            "color" to "أخضر",
            "location" to "طنطا",
            "image" to R.drawable.cars
        ),

        mapOf(
            "nationalId" to "مريم أحمد",
            "plateNumber" to "ب د ر123",
            "color" to "ازرق",
            "location" to "القاهرة",
            "image" to R.drawable.blue_car
        ),

        )

     fun onSearchAboutCar(navigator: DestinationsNavigator, context: Context){
         job?.cancel()
         job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
             val response = searchAboutCarUseCase(
                 number = "",
                 context = context
             )
            // state = state.copy(isLoginLoading = false)

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
    fun onConfirmTransaction(navigator: DestinationsNavigator, context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
            val response = confirmTransactionUseCase(
                address = "",
                context = context
            )
            // state = state.copy(isLoginLoading = false)

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
    fun onGetTransaction(navigator: DestinationsNavigator, context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
            val response = getTransactionUseCase(
                address = "",
                context = context
            )
            // state = state.copy(isLoginLoading = false)

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
    fun onSendTransaction(navigator: DestinationsNavigator, context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
            val response = sendTransactionUseCase(
                sendTransactionRequest = SendTransactionRequest(
                    address = "",
                    carId = ""
                ),
                context = context
            )
            // state = state.copy(isLoginLoading = false)

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
    fun onTransferOwnerShip(navigator: DestinationsNavigator, context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
            val response = transferOwnerShipUseCase(
                transferOwnerShipRequest = TransferOwnerShipRequest(
                    carId = "",
                    address = "",
                    toAddress = ""
                ),
                context = context
            )
            // state = state.copy(isLoginLoading = false)

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

    fun onGetCars(navigator: DestinationsNavigator, context: Context){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            // state = state.copy(isLoginLoading = true)
            val response = getCarsUseCase(
                address = UserPreferences.getUserPrivateAddress(context) ?:"",
                context = context
            )
            // state = state.copy(isLoginLoading = false)

            if (response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
                Log.d("not workedddd", "onGetCars: $response ")
                Log.d("nss", "onGetCars: ${UserPreferences.getUserPrivateAddress(context)} ")
            } else {
                response.data?.let { listOfCars.add(it) }
                Log.d("workedddd", "onGetCars: ${listOfCars} ")
                /*viewModelScope.launch(Dispatchers.Main) {
                    navigator.popBackStack()
                    navigator.navigate(MainScreenDestination())
                }*/

            }

        }
    }

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