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
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.destinations.CompletePaymentScreenDestination
import com.example.engaz.destinations.RequestsScreenDestination
import com.example.engaz.destinations.WalletPageDestination
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.example.engaz.features.profile.view.components.BackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun AcceptedRequestDetails(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth()) {
            BackButton(onClick = {
                navigator?.let {
                    onBackArrowClick(navigator)
                }
            })

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = stringResource(R.string.sent_requests_ar),
                fontSize = 20.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        InfoAboutCarCard(
            isRequest = false,
            pendingRequest = true,
            showButtons = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "طريقة الدفع",
            fontSize = 20.sp,
            fontFamily = Cairo,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center
        )
        Card(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .clickable {
                    navigator?.navigate(WalletPageDestination)
                }, shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp,Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Payment,
                    contentDescription = ""
                )

                Text(
                    modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically),
                    text = "اختر طريقة الدفع",
                    fontSize = 16.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {
                navigator?.navigate(CompletePaymentScreenDestination)
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