package com.example.engaz.features.auth.view.screens.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.core.ui.theme.*
import com.example.engaz.features.auth.view.viewmodels.login.LoginState
import com.example.engaz.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("ResourceType", "UnrememberedMutableState")
@Destination
@Composable
fun LoginWithFingerPrintScreen(
    navigator: DestinationsNavigator?,
    state: LoginState = LoginState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {

    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isScanningFingerprint = false


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) colorResource(id = R.color.primary_color2) else colorResource(
            id = R.color.primary_color2
        )
    ) {
        it
        Box() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))

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
                    tint = if (isSystemInDarkTheme()) Color.White else Color.White
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text =if(isScanningFingerprint) stringResource(R.string.scaing_fingerprint) else stringResource(R.string.fingerprint_description_ar),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.put_your_finger),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )


            }

            Image(
                painter = painterResource(id = R.drawable.ic_finger_print),
                contentDescription = "finger_print",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .combinedClickable(onLongClick = {
                        isScanningFingerprint = true
                    }) {
                        isScanningFingerprint = false
                    }
            )
        }

    }

}




