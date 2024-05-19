package com.example.engaz.features.home.view.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.features.home.view.pages.home.HomePage
import com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership.TransferCarOwnershipScreen
import com.example.engaz.features.home.view.viewmodels.home.HomeEvent
import com.example.engaz.features.home.view.viewmodels.home.HomeViewModel
import com.example.engaz.features.profile.view.pages.ProfilePage
import com.example.engaz.features.wallet.view.pages.WalletPage
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class BottomNavPage(
    val page: @Composable (DestinationsNavigator,Context) -> Unit,
    val icon: Int,
    val label: Int
) {
    data object HomeNavPage : BottomNavPage(
        page = { navigator , context ->
            val viewModel : HomeViewModel = hiltViewModel()
            HomePage(
                navigator = navigator,
                state = viewModel.state,
                init = {context ->  },
                onSendOrderClick = { navigator,context ->  viewModel.onEvent(HomeEvent.OnMakeOrderClick(navigator,context))},
            )
        },
        icon = R.drawable.ic_home,
        label = R.string.home
    )

    data object TransferOwnership : BottomNavPage(
        page = {navigator , context ->
            TransferCarOwnershipScreen(
                navigator = navigator,
            )
        },
        icon = R.drawable.ic_ownership,
        label = R.string.orders
    )

    data object Payment : BottomNavPage(
        page = {navigator , context ->
            val viewModel : WalletViewModel = hiltViewModel()

            WalletPage(
                navigator = navigator,
                state = viewModel.state,
            )
        },
        icon = R.drawable.ic_payment,
        label = R.string.wallet
    )

    data object ProfileNavPage : BottomNavPage(
        page = {navigator , context ->
            ProfilePage(
                navigator = navigator,
            )
        },
        icon = R.drawable.profile,
        label = R.string.profile
    )

}

