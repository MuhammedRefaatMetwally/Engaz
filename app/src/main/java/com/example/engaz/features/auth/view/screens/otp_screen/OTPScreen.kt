package com.example.engaz.features.auth.view.screens.otp_screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.core.views.components.CustomProgressIndicator
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.features.auth.view.components.reset_password.PinField
import com.example.engaz.features.auth.view.viewmodels.register.RegisterState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination()
@Composable
fun OTPScreen(
    navigator: DestinationsNavigator?,
    state: RegisterState = RegisterState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onPinChangeClick: (String) -> Unit = {},
    onValidateClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onResendClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onComplete: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
) {
    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()
    var otp by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.otp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enter the Verification Code",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter the 4 digit number that we sent to ${state.email}.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            PinField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                value = state.otpCode,
                onValueChange = {
                    onPinChangeClick(it)
                },
                onComplete = {
                    navigator?.let {
                        onComplete(navigator, context)
                    }
                }
            )
            Spacer(modifier = Modifier.height(80.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        scope.launch {
                            navigator?.let {
                                onValidateClick(navigator, context)
                            }
                        }
                    },
                cardColor = Primary,
                borderColor = Color.Transparent
            ) {

                if (!state.isValidatingPinCode) {
                    Text(
                        text = "Verify",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )
                } else {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = {   navigator?.let {
                    onResendClick(navigator,context)
                } }
            ) {
                if (!state.isResendingPinCode) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                Color.Gray,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp
                            )
                        ) {
                            append("Didn’t Receive Anything?")
                        }
                        withStyle(
                            SpanStyle(
                                colorResource(id = R.color.primary_color),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp
                            )
                        ) {
                            append(" Resend Code")
                        }


                    }, color = Color(0xFF6200EE))

                }else{
                    CustomProgressIndicator( modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}
