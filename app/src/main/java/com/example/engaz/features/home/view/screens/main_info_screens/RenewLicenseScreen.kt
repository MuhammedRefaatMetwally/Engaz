package com.example.engaz.features.home.view.screens.main_info_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.destinations.WalletPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun RenewLicenseScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    val scroll = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }
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
            label = stringResource(R.string.license_number)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.owner_name)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.end_date_ar)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.info_about_car)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.current_address)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.email_ar)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.phone_number_ar)
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            label = stringResource(R.string.request_number_ar),
        )

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.payment_way_ar),
            fontWeight = FontWeight.W700,
            fontFamily = Cairo,
            fontSize = 16.sp
        )
        androidx.compose.material3.Card(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .clickable {
                    navigator?.navigate(WalletPageDestination)
                }, shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Payment,
                    contentDescription = ""
                )

                androidx.compose.material3.Text(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically),
                    text = "اختر طريقة الدفع",
                    fontSize = 16.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp)
                .clickable { showDialog.value = true },
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