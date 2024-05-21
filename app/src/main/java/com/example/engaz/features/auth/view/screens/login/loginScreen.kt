package com.example.engaz.features.auth.view.screens.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.*
import com.example.engaz.features.auth.view.viewmodels.login.LoginState
import com.example.engaz.R
import com.example.engaz.core.util.biometericAuth.BiometricPromptManager
import com.example.engaz.core.util.biometericAuth.BiometricPromptManager.*
import com.example.engaz.destinations.MainScreenDestination
import com.example.engaz.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("ResourceType", "UnrememberedMutableState")
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator?,
    state: LoginState = LoginState(),
    onChangeEmailOrPassCode: (String) -> Unit = {},
    onChangePhoneWithCountryCode: (PhoneNumber) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onSecurePasswordClick: () -> Unit = {},
    onLoginClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onRememberMeClick: () -> Unit = {},
    onForgotPasswordClick: (DestinationsNavigator) -> Unit = {},
    onRegisterClick: (DestinationsNavigator) -> Unit = {},
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    val context = LocalContext.current as FragmentActivity

    val promptManager by lazy {
        BiometricPromptManager(context)
    }
    val biometricResult by promptManager.promptResults.collectAsState(
        initial = null
    )
    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            println("Activity result: $it")
        }
    )
    LaunchedEffect(biometricResult) {
        if (biometricResult is BiometricResult.AuthenticationNotSet) {
            if (Build.VERSION.SDK_INT >= 30) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                enrollLauncher.launch(enrollIntent)
            }
        }
    }

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LeftToRightLayout {
                    Image(
                        painter = painterResource(id = R.drawable.logo1), // Provide the resource ID
                        contentDescription = "",
                        modifier = Modifier.size(120.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.login_ar),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = state.phoneOrPassCode,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    onChangeEmailOrPassCode(it)
                },
                placeHolder = stringResource(R.string.please_enter_username_passcode),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = ""
                    )
                },
                errorMessage = state.phoneOrPassCodeError ?:"",
                isError =  state.phoneOrPassCodeError != null,
                label = stringResource(R.string.account_label)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                isSecure = state.isPasswordSecure,
                value = state.password,
                onValueChange = {
                    onChangePassword(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeHolder = stringResource(R.string.please_enter_password),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier
                            .padding(end = 0.dp)
                            .clickable {
                                onSecurePasswordClick()
                            },
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = ""
                    )
                },
                isError = state.passwordError != null,
                errorMessage = state.passwordError ?: "",
                label = stringResource(R.string.password_label)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.forget_password_ar),
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                color = colorResource(id = R.color.primary_color),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 18.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp
                        )
                    ) {
                        append(stringResource(R.string.have_an_account_ar))
                    }

                    withStyle(
                        SpanStyle()
                    ) {
                        append(" ")
                    }
                    withStyle(
                        SpanStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp,
                            color = colorResource(id = R.color.primary_color)
                        )
                    ) {
                        append(stringResource(R.string.create_an_account_ar))
                    }

                },
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navigator?.navigate(RegisterScreenDestination)
                    }
            )

            Spacer(modifier = Modifier.height(12.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            onLoginClick(navigator,context)
                            //navigator.navigate(MainScreenDestination)
                        }
                    },
                cardColor = colorResource(id = R.color.primary_color),
                borderColor = Color.Transparent
            ) {
                if (state.isLoginLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(id = R.string.login_ar),
                        style = TextStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                        .weight(.4f)
                        .height(1.7.dp),
                    color = Neutral300
                )

                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 10.dp),
                    text = " أو ",
                    style = TextStyle(
                        fontFamily = Cairo,
                        color = Color.Black,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.End
                )

                HorizontalDivider(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .fillMaxWidth()
                        .weight(.4f)
                        .height(1.7.dp),
                    color = Neutral300
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        promptManager.showBiometricPrompt(
                            title = "تسجيل الدخول",
                            description = "استخدم البصمة لتسجيل الدخول"
                        )
                    },
                cardColor = Color.White,
                borderColor = Neutral300
            ) {
                if (state.isLoginLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(id = R.drawable.ic_finger),
                            contentDescription = "finger_print",
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = stringResource(R.string.login_with_fingeprint),
                            style = TextStyle(
                                fontFamily = Cairo,
                                fontWeight = FontWeight.W700,
                                color = Color.Black,
                                fontSize = 15.sp,
                            )
                        )
                    }
                }
            }

            biometricResult?.let { result ->
                  when(result) {
                    is BiometricResult.AuthenticationError -> {
                        result.error
                    }
                    BiometricResult.AuthenticationFailed -> {
                        "Authentication failed"
                    }
                    BiometricResult.AuthenticationNotSet -> {
                        "Authentication not set"
                    }
                    BiometricResult.AuthenticationSuccess -> {
                        navigator?.navigate(MainScreenDestination)
                    }
                    BiometricResult.FeatureUnavailable -> {
                        "Feature unavailable"
                    }
                    BiometricResult.HardwareUnavailable -> {
                        "Hardware unavailable"
                    }

                      else -> Unit
                  }
            }
        }
    }

}



