package com.example.engaz.features.home.view.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.features.home.view.pages.home.HomePage
import com.example.engaz.features.home.view.screens.main_info_screens.InfoAboutCarScreen
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
        label = R.string.home_ar
    )

    data object TransferOwnership : BottomNavPage(
        page = {navigator , context ->
            TransferCarOwnershipScreen(
                navigator = navigator,
            )
        },
        icon = R.drawable.ic_ownership,
        label = R.string.transfering_ownership_ar
    )

    data object InfoAboutCarScreen : BottomNavPage(
        page = {navigator , context ->
            InfoAboutCarScreen(
                navigator = null,
            )
        },
        icon = R.drawable.questionnaire,
        label = R.string.questionnair
    )

    data object ProfileNavPage : BottomNavPage(
        page = {navigator , context ->
            ProfilePage(
                navigator = null,
            )
        },
        icon = R.drawable.profile,
        label = R.string.profile_ar
    )

}

