package com.example.engaz.features.wallet.view.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
import com.example.engaz.features.wallet.view.components.WalletItem
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WalletPage(
    navigator: DestinationsNavigator?,
    state: WalletState = WalletState(),
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


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Row(Modifier.fillMaxWidth()) {
                if(navigator!=null){
                    Icon(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .clickable {
                                navigator.let { }
                            },
                        painter = painterResource(
                            id = R.drawable.arrow_left
                        ),
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
                    )
                }


                Text(
                    modifier = Modifier.padding(start = 124.dp, bottom = 8.dp),
                    text = stringResource(R.string.choose_payment_way),
                    fontSize = 20.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            walletIcons.forEachIndexed { index, item ->
                WalletItem(paymentIcon = item, paymentName = walletNames[index])
                Spacer(modifier = Modifier.height(24.dp))
            }
            val stroke = Stroke(
                width = 4f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
            )
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(100.dp)
                    .drawBehind {
                        drawRoundRect(color = Primary, style = stroke)
                    }.clickable { }, shape = RoundedCornerShape(0.dp),
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

    }


}


@Preview
@Composable
fun WalletPagePreview() {
    WalletPage(navigator = null)
}