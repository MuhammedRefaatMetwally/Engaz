package com.example.engaz.features.auth.view.screens.register

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import com.example.engaz.R
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.*
import com.example.engaz.destinations.RegisterScreenDestination
import com.example.engaz.features.auth.view.viewmodels.register.RegisterState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.togitech.ccp.component.TogiCountryCodePicker
import com.togitech.ccp.data.CountryData
import kotlinx.coroutines.launch

@SuppressLint("ResourceType", "UnrememberedMutableState")
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator?,
    state: RegisterState = RegisterState(),
    onChangeFullName: (String) -> Unit = { },
    onChangePassCode: (String) -> Unit = {},
    onChangeEmail: (String) -> Unit = {},
    onChangePhone: (String, Context) -> Unit = { _, _ -> },
    onChangePhoneWithCountryCode: (PhoneNumber) -> Unit = {},
    onChangePassword: (String) -> Unit = {},
    onChangePasswordRenter: (String) -> Unit = {},
    onSecurePasswordClick: () -> Unit = {},
    onSecurePasswordRenterClick: () -> Unit = {},
    onLoginClick: (DestinationsNavigator) -> Unit = {},
    onRegisterClick: (DestinationsNavigator, Context) -> Unit = { _, _ -> },
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onTermsClick: () -> Unit = {},
) {

    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) { padding ->
        padding

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(10.dp))

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


            Spacer(modifier = Modifier.height(20.dp))


            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Start),
                text = stringResource(R.string.new_user_ar),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = state.fullName,
                onValueChange = { onChangeFullName(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeHolder = stringResource(R.string.user_name_ar),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.profile_inactive),
                        contentDescription = ""
                    )
                },
                isError = state.fullNameError != null,
                errorMessage = state.fullNameError ?: "",
                label = stringResource(R.string.user_name_ar)
            )

            Spacer(modifier = Modifier.height(8.dp))

            var phoneNumber: String by rememberSaveable { mutableStateOf("") }
            var fullPhoneNumber: String by rememberSaveable { mutableStateOf("") }
            var isNumberValid: Boolean by rememberSaveable { mutableStateOf(false) }

            TogiCountryCodePicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onValueChange = { (code, phone), isValid ->

                    phoneNumber = phone
                    fullPhoneNumber = code + phone
                    isNumberValid = isValid
                },
                fallbackCountry = CountryData.Egypt,
                label = { Text(stringResource(id = R.string.phone_number_ar)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedLabelColor = Neutral500,
                    focusedBorderColor = colorResource(id = R.color.primary_color),
                    errorBorderColor = colorResource(id = R.color.primary_color),
                    errorTrailingIconColor = colorResource(id = R.color.primary_color),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = state.passCode,
                onValueChange = {
                    onChangePassCode(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeHolder = stringResource(R.string.pass_code),
                isError = state.passCodeError != null,
                errorMessage = state.passCodeError ?: "",
                label = stringResource(R.string.passcode)

            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                isSecure = state.isPasswordSecure,
                value = state.password,
                onValueChange = {

                    onChangePassword(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
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

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                isSecure = state.isPasswordRenterSecure,
                value = state.passwordRenter,
                onValueChange = {

                    onChangePasswordRenter(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                placeHolder = stringResource(R.string.reenter_password),
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
                label = stringResource(R.string.reenter_password)
            )

            Spacer(modifier = Modifier.height(132.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp
                        )
                    ) {
                        append(stringResource(R.string.have_an_account_ar2))
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
                            fontSize = 16.sp, color = colorResource(id = R.color.primary_color)
                        )
                    ) {
                        append(stringResource(R.string.login_ar2))
                    }

                },
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {

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
                            //navigator.navigate(RegisterScreenDestination)
                        }
                    },
                cardColor = colorResource(id = R.color.primary_color),
                borderColor = Color.Transparent
            ) {

                if (state.isRegisterLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(R.string.login_now),
                        style = TextStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    )
                }

            }
        }
        }
}

@Preview
@Composable
fun DefaultPreview() {
    MarketAppTheme {
        RegisterScreen(
            navigator = null
        )
    }
}


