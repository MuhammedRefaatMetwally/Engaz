package com.example.engaz.features.home.view.viewmodels.main_info.transfer_car_ownership

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class TransferCarOwnerShipEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : TransferCarOwnerShipEvent()

}
