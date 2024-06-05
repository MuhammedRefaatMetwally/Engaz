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
    private val chargeBalanceUseCase: ChargeBalanceUseCase,
    private val getBalanceUseCase: GetBalanceUseCase,
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


   /* fun updateImageState(image: Uri?) {
        if(image != null){
            state = state.copy(moneyTransferPhoto = image)
        }
    }

    private fun onGetBalance(context: Context) {
        if (job == null){
            state = state.copy(balanceError = null)

            job = viewModelScope.launch(Dispatchers.IO) {
                state = state.copy(balanceIsLoading = true)
                val response = getBalanceUseCase(
                    (CoreViewModel.user?.token ?: ""),
                    context,
                )
                state = state.copy(balanceIsLoading = false)

                if(response.failure != null) {
                    state = state.copy(balanceError = response.failure.message)

                } else {
                    state = state.copy(balance = response.data?.data?.wallet?.balance?.toDouble()?: 0.0)
                }
            }
            job = null
        }
    }

    private fun onBalanceRecharge(navigator: DestinationsNavigator, context: Context) {

        if(state.moneyTransferPhoto == null) {
            state = state.copy(chargeBalanceError = context.getString(R.string.select_image))
            return
        }

        if (job == null){
            state = state.copy(chargeBalanceError = null)

            job = viewModelScope.launch(Dispatchers.IO) {
                state = state.copy(chargeBalanceIsLoading = true)
                val response = chargeBalanceUseCase(
                    CoreViewModel.user!!.token,
                    state.moneyTransferPhoto!!,
                    context,
                )
                state = state.copy(chargeBalanceIsLoading = false)

                if(response.failure != null) {
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        navigator.navigate(WalletMessageScreenDestination)
                    }

                }
            }
            job = null
        }
    }
*/


}