package com.example.engaz.features.home.view.viewmodels.main_info.renew_license

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class RenewLicenseViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(RenewLicenseState())
    private var job: Job? = null

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }
    fun onEvent(event : RenewLicenseEvent) {
        when(event){
            is RenewLicenseEvent.OnBackClick -> {
                onBackClick(event.navigator)
            }
        }
    }

    }
