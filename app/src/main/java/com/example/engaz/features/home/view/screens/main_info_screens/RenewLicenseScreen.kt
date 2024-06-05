package com.example.engaz.features.home.view.screens.main_info_screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.features.home.view.viewmodels.main_info.renew_license.RenewLicenseState
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.PaymentSheetResultCallback
import com.stripe.android.paymentsheet.rememberPaymentSheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Destination
fun RenewLicenseScreen(
    navigator: DestinationsNavigator?,
    state: RenewLicenseState = RenewLicenseState(),
    showDialog: MutableState<Boolean>,
    paymentViewModel: WalletViewModel = hiltViewModel(),
    onChangeLicenseNumber: (String) -> Unit = {},
    onChangeDriverName: (String) -> Unit = {},
    onChangeExpiryDate: (String) -> Unit = {},
    onChangeCarDescription: (String) -> Unit = {},
    onChangeCurrentAddress: (String) -> Unit = {},
    onChangeEmail: (String) -> Unit = {},
    onChangePhoneNumber: (String) -> Unit = {},
    onChangeRequestNumber: (String) -> Unit = {},
    onRenewLicense: (DestinationsNavigator?, Context) -> Unit = { _, _ -> },
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current



    if (showDialog.value)
        CustomDialog(
            processText = "تم إستلام طلب تجديد الرخصة",
            buttonText = "تحميل رخصة القيادة",
            navigator = navigator,
            setShowDialog = {
                showDialog.value = it
            })

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .clickable {
                        navigator?.let {
                            onBackArrowClick(navigator)
                        }
                    },
                painter = painterResource(
                    id = R.drawable.arrow_left
                ),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
            )

            Text(
                modifier = Modifier.padding(start = 110.dp, bottom = 8.dp),
                text = stringResource(R.string.renew_license_ar),
                fontSize = 20.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.license_number),
            value = state.licenseNumber,
            errorMessage = state.licenseNumberError ?: "",
            isError = state.licenseNumberError != null,
            onValueChange = {
                onChangeLicenseNumber(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.owner_name),
            value = state.driverName,
            errorMessage = state.driverNameError ?: "",
            isError = state.driverNameError != null,
            onValueChange = {
                onChangeDriverName(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.end_date_ar),
            value = state.expiryDate,
            errorMessage = state.expiryDateError ?: "",
            isError = state.expiryDateError != null,
            onValueChange = {
                onChangeExpiryDate(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.info_about_car),
            value = state.carDescription,
            errorMessage = state.carDescriptionError ?: "",
            isError = state.carDescriptionError != null,
            onValueChange = {
                onChangeCarDescription(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.current_address),
            value = state.currentAddress,
            errorMessage = state.currentAddressError ?: "",
            isError = state.currentAddressError != null,
            onValueChange = {
                onChangeCurrentAddress(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.email_ar),
            value = state.email,
            errorMessage = state.emailError ?: "",
            isError = state.emailError != null,
            onValueChange = {
                onChangeEmail(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.phone_number_ar),
            value = state.phone,
            errorMessage = state.phoneError ?: "",
            isError = state.phoneError != null,
            onValueChange = {
                onChangePhoneNumber(it)
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.request_number_ar),
            value = state.requestNumber,
            errorMessage = state.requestNumberError ?: "",
            isError = state.requestNumberError != null,
            onValueChange = {
                onChangeRequestNumber(it)
            },
            isNumber = true
        )


        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp)
                .clickable {
                    onRenewLicense(navigator, context)
                },
            cardColor = Primary,
        ) {
            Text(
                text = stringResource(R.string.renew_ar), fontWeight = FontWeight.W700,
                fontFamily = Cairo,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}


fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    paymentIntentClientSecret: String,
    customerConfig: PaymentSheet.CustomerConfiguration
) {
    paymentSheet.presentWithPaymentIntent(
        paymentIntentClientSecret,
        PaymentSheet.Configuration(
            merchantDisplayName = "crow",
            customer = customerConfig
        )
    )
}

fun onPaymentSheetResult(
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