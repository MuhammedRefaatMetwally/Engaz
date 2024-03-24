package com.example.engaz.features.home.view.viewmodels.main_info.renew_license

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class RenewLicenseEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : RenewLicenseEvent()

}
