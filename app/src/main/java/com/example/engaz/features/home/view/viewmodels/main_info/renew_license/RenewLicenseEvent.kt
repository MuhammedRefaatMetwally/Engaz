package com.example.engaz.features.home.view.viewmodels.main_info.renew_license

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class RenewLicenseEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : RenewLicenseEvent()
    data class OnRenewLicense(val navigator: DestinationsNavigator, var context: Context, var onPayment:()->Unit) : RenewLicenseEvent()

}
