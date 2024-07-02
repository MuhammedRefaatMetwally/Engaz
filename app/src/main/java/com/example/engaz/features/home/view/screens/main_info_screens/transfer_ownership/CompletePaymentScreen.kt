package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.features.auth.view.viewmodels.login.LoginViewModel
import com.example.engaz.features.home.view.viewmodels.main_info.complete_payment.CompletePaymentState
import com.example.engaz.features.profile.view.components.BackButton
import com.example.engaz.features.wallet.view.components.AddPaymentCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Destination
fun CompletePaymentScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onChangeCVC: (String) -> Unit = {},
    loginViewModel: LoginViewModel = hiltViewModel(),
    state: CompletePaymentState = CompletePaymentState(),
    onSecureCVCClick: () -> Unit = {},
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        CustomDialog(
            processText = "تمت عملية الدفع",
            buttonText = "إرسال إيصال",
            navigator = navigator,
            setShowDialog = {
                showDialog.value = it
            })

    Column {
        Spacer(modifier = Modifier.height(24.dp))
        BackButton(onClick = {
            navigator?.let {
                onBackArrowClick(navigator)
            }
        })

        Spacer(modifier = Modifier.height(40.dp))

        AddPaymentCard(showDialog)

    }
    }



