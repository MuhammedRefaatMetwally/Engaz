package com.example.engaz.features.home.view.viewmodels.main_info.renew_license

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.util.usecase.ValidateEmailLocalUseCase
import com.example.engaz.core.util.usecase.ValidatePhoneLocalUseCase
import com.example.engaz.features.home.domain.usecases.ValidateCarDescriptionUseCase
import com.example.engaz.features.home.domain.usecases.ValidateCurrentAddressUseCase
import com.example.engaz.features.home.domain.usecases.ValidateDriverNameUseCase
import com.example.engaz.features.home.domain.usecases.ValidateExpiryDateUseCase
import com.example.engaz.features.home.domain.usecases.ValidateLicenseNumberUseCase
import com.example.engaz.features.home.domain.usecases.ValidateRequestNumberUseCase
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreateEpheralKeyResponse
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreatePaymentIntentResponse
import com.example.engaz.features.wallet.data.repo.PaymentRepository
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RenewLicenseViewModel @Inject constructor(
    private val validateCarDescriptionUseCase: ValidateCarDescriptionUseCase,
    private val currentAddressUseCase: ValidateCurrentAddressUseCase,
    private val driverNameUseCase: ValidateDriverNameUseCase,
    private val expiryDateUseCase: ValidateExpiryDateUseCase,
    private val licenseNumberUseCase: ValidateLicenseNumberUseCase,
    private val requestNumberUseCase: ValidateRequestNumberUseCase,
    private val validateEmailLocalUseCase: ValidateEmailLocalUseCase,
    private val validatePhoneLocalUseCase: ValidatePhoneLocalUseCase,
    private val repository: PaymentRepository

) : ViewModel() {

    var state by mutableStateOf(RenewLicenseState())
    val showDialog = mutableStateOf(false)
    private var job: Job? = null

    val paymentIntentResponse = mutableStateOf(CreatePaymentIntentResponse())



    private fun updatePaymentIntentResponse(paymentIntentResponse: CreatePaymentIntentResponse){
        state = state.copy(
            paymentIntentResponse =  paymentIntentResponse
        )
    }

    fun updateCarDescription(carDescription: String){
        state = state.copy(
            carDescription = carDescription
        )
    }

    fun updateCurrentAddress(currentAddress: String){
        state = state.copy(
            currentAddress = currentAddress
        )
    }

    fun updateDriverName(driverName: String){
        state = state.copy(
            driverName = driverName
        )
    }

    fun updateExpiryDate(expiryDate: String){
        state = state.copy(
            expiryDate = expiryDate
        )
    }

    fun updateLicenseNumber(licenseNumber: String){
        state = state.copy(
            licenseNumber = licenseNumber
        )
    }

    fun updateRequestNumber(requestNumber: String){
        state = state.copy(
            requestNumber = requestNumber
        )
    }

    fun updateEmail(email: String){
        state = state.copy(
            email = email
        )
    }

    fun updatePhone(phone: String){
        state = state.copy(
            phone = phone
        )
    }
    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }



    fun onEvent(event: RenewLicenseEvent) {
        when (event) {
            is RenewLicenseEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }

            is RenewLicenseEvent.OnRenewLicense -> {
              validateForm(event.context){
                  event.onPayment()
              }
            }
        }
    }

    private fun validateForm(context: Context, callBackFunction: () -> Unit) {

        val licenseNumber = licenseNumberUseCase(state.licenseNumber, context)
        val phoneResult = validatePhoneLocalUseCase(state.phone, context)
        val driverName = driverNameUseCase(state.driverName, context)
        val expiryDate = expiryDateUseCase(state.expiryDate, context)
        val carDescription = validateCarDescriptionUseCase(state.carDescription, context)
        val currentAddress = currentAddressUseCase(state.currentAddress, context)
        val email = validateEmailLocalUseCase(state.email, context)
        val requestNumber = requestNumberUseCase(state.requestNumber, context)


        val hasError = listOf(
            licenseNumber,
            phoneResult,
            driverName,
            expiryDate,
            carDescription,
            currentAddress,
            email,
            requestNumber,
        ).any {
            it.failure != null
        }

        state = state.copy(
            licenseNumberError = licenseNumber.failure?.message,
            phoneError = phoneResult.failure?.message,
            driverNameError = driverName.failure?.message,
            expiryDateError = expiryDate.failure?.message,
            carDescriptionError = carDescription.failure?.message,
            currentAddressError= currentAddress.failure?.message,
            emailError = email.failure?.message,
            requestNumberError = requestNumber.failure?.message,
        )

        if (hasError) {
            showDialog.value = false
            return
        }

        callBackFunction()

    }

}
