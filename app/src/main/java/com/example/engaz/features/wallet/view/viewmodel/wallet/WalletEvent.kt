package com.example.engaz.features.wallet.view.viewmodel.wallet

import android.content.Context
import com.example.engaz.features.home.view.viewmodels.main_info.info_about_service.HomeInfoEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class WalletEvent {

    data class OnBackClick(val navigator: DestinationsNavigator) : WalletEvent()
    class OnBalanceRecharge(val navigator: DestinationsNavigator,val context: Context): WalletEvent()

    class OnGetBalance(val context: Context): WalletEvent()

}
