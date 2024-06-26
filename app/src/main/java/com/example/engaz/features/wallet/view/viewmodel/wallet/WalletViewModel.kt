package com.example.engaz.features.wallet.view.viewmodel.wallet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.engaz.core.util.Consts
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreatePaymentIntentResponse
import com.example.engaz.features.wallet.data.repo.PaymentRepository
import com.example.engaz.features.wallet.domain.usecase.ChargeBalanceUseCase
import com.example.engaz.features.wallet.domain.usecase.GetBalanceUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

    var state by mutableStateOf (WalletState())
    private var job : Job? = null
    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }
    fun onEvent(event: WalletEvent){
        when(event){
            is WalletEvent.OnBalanceRecharge -> {
               // onBalanceRecharge(event.navigator,event.context)
            }
            is WalletEvent.OnGetBalance -> {
               // onGetBalance(event.context)
            }

            is WalletEvent.OnBackClick -> {
                onBackClick(navigator = event.navigator)
            }
        }
    }


    suspend fun makePayment(onPaymentDetailsFetched: (clientSecret: String?, customerId: String?, ephemeralKey: String?) -> Unit) {
        val paymentIntentResponse = repository.createPaymentIntent()
        val ephemeralKeyResponse = repository.refreshCustomerEphemeralKey()
        val clientSecret = paymentIntentResponse.clientSecret
        val customerId = paymentIntentResponse.customer
        val ephemeralKey = ephemeralKeyResponse.secret
        onPaymentDetailsFetched(clientSecret, customerId, ephemeralKey)
    }



    fun handlePaymentResult(result: PaymentSheetResult) {
        when(result) {
            PaymentSheetResult.Canceled -> TODO()
            PaymentSheetResult.Completed -> TODO()
            is PaymentSheetResult.Failed -> TODO()
        }
    }




}