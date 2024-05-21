package com.example.engaz.features.wallet.view.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.destinations.IdentityScreenDestination
import com.example.engaz.features.profile.view.components.BackButton
import com.example.engaz.features.wallet.view.components.WalletItem
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletState
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun WalletPage(
    navigator: DestinationsNavigator?,
    state: WalletState = WalletState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {

    val walletIcons = listOf(
        R.drawable.ic_visa,
        R.drawable.ic_master_card,
        R.drawable.ic_paypal,
        R.drawable.ic_apple_pay,
    )
    val walletNames = listOf(
        "VisaCard",
        "MasterCard",
        "PayPal",
        "ApplePay",
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp)
    ) {
        item { Spacer(modifier = Modifier.height(30.dp)) }

        item {
            Row(Modifier.fillMaxWidth()) {
                if (navigator != null) {
                    BackButton(onClick = { onBackArrowClick(navigator) })
                }
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    text = stringResource(R.string.choose_payment_way),
                    fontSize = 20.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            }
        }

        item { Spacer(modifier = Modifier.height(32.dp)) }

        items(walletIcons.size) { index ->
            WalletItem(
                paymentIcon = walletIcons[index],
                paymentName = walletNames[index]
            ) {
                navigator?.navigate(IdentityScreenDestination)
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        val stroke = Stroke(
            width = 8f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
        )

        item {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(100.dp)
                    .drawBehind {
                        drawRoundRect(color = Primary, style = stroke)
                    }
                    .clickable { }, shape = RoundedCornerShape(0.dp),
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                        tint = Primary
                    )
                    Text(
                        text = stringResource(R.string.adding_new_payment_ar),
                        fontFamily = Cairo,
                        fontWeight = FontWeight.W400,
                        fontSize = 20.sp
                    )
                }
            }
        }
        
        item { 
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


