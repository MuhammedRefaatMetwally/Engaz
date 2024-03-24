package com.example.engaz.features.home.view.viewmodels.main_info.complete_payment

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class CompletedPaymentEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : CompletedPaymentEvent()

}
