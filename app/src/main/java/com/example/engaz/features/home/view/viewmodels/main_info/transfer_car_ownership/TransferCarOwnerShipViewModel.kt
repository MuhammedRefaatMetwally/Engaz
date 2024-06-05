package com.example.engaz.features.home.view.viewmodels.main_info.transfer_car_ownership

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoCarEvent
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class TransferCarOwnerShipViewModel @Inject constructor() : ViewModel() {
    val showDialog = mutableStateOf(false)
    var state by mutableStateOf(HomeInfoState())
    private var job: Job? = null

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    fun onEvent(event : TransferCarOwnerShipEvent) {
        when(event){
            is TransferCarOwnerShipEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
            is TransferCarOwnerShipEvent.OnAcceptRequest ->{
                event.onAcceptPayment()
            }
        }
    }

    }
