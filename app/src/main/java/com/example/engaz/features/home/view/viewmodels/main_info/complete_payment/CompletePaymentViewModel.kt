package com.example.engaz.features.home.view.viewmodels.main_info.complete_payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class CompletePaymentViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(CompletePaymentState())
    private var job: Job? = null

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }
    fun updateCvc(cvc : String){
        state = state.copy(
            cvc = cvc
        )
    }


    fun updateCVCSecureState(){
        state = state.copy(
            isCVCSecure = !state.isCVCSecure
        )
    }
    fun onEvent(event : CompletedPaymentEvent) {
        when(event){
            is CompletedPaymentEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
        }
    }

    }
