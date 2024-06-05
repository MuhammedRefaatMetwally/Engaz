package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.core.views.components.MainButton
import io.github.raamcosta.compose_destinations.destinations.CompletePaymentScreenDestination
import io.github.raamcosta.compose_destinations.destinations.RequestsScreenDestination
import io.github.raamcosta.compose_destinations.destinations.WalletPageDestination
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.example.engaz.features.home.view.viewmodels.main_info.transfer_car_ownership.TransferCarOwnerShipViewModel
import com.example.engaz.features.profile.view.components.BackButton
import com.example.engaz.features.profile.view.components.Header
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.stripe.android.paymentsheet.PaymentSheetResult
import io.grpc.Context

@Composable
@Destination
fun AcceptedRequestDetails(
    navigator: DestinationsNavigator?,
    transferCarOwnerShipViewModel: TransferCarOwnerShipViewModel= hiltViewModel(),
    onAcceptRequest :() -> Unit,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {

    if (transferCarOwnerShipViewModel.showDialog.value)
        CustomDialog(
            processText = "تمت عملية الدفع",
            buttonText = "إرسال إيصال",
            navigator = navigator,
            setShowDialog = {
                transferCarOwnerShipViewModel.showDialog.value = it
            })

    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth()) {
            Header(label = stringResource(R.string.sent_requests_ar)){
                navigator?.let {
                    onBackArrowClick(navigator)
                }
            }

        }
        Spacer(modifier = Modifier.height(24.dp))
        InfoAboutCarCard(
            isRequest = false,
            pendingRequest = true,
            showButtons = false
        )

        Spacer(modifier = Modifier.height(32.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {
                    onAcceptRequest()
                },
            cardColor = colorResource(id = R.color.primary_color)
        ) {
            Text(
                text = "متابعة",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

fun onPaymentSheetAcceptedRequestResult(
    paymentSheetResult: PaymentSheetResult,
    showDialog: MutableState<Boolean>
) {

    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            println("Canceled")
        }

        is PaymentSheetResult.Failed -> {
            println("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            showDialog.value = true
            println("Completed")
        }
    }
}