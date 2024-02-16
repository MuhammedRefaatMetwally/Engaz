package com.example.engaz.features.auth.view.screens.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.*
import com.example.engaz.features.auth.view.viewmodels.login.LoginState
import com.example.engaz.R
import com.example.engaz.destinations.RegisterScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

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

    val context: Context = LocalContext.current
    val scope = rememberCoroutineScope()



    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it

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
                tint = if (isSystemInDarkTheme()) Neutral100 else Neutral900
            )



            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.engaz_logo2), // Provide the resource ID
                    contentDescription = "",
                    modifier = Modifier
                        .size(104.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.login_ar),
                style = TextStyle(
                    fontFamily = Cairo,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                    fontSize = 32.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))


            CustomTextField(
                value = state.emailOrPassCode,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onValueChange = {
                    onChangeEmailOrPassCode(it)
                },
                placeHolder = stringResource(R.string.please_enter_email_passcode),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 0.dp),
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = ""
                    )
                },
                label = stringResource(R.string.account_label)
            )
            Spacer(modifier = Modifier.height(8.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

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
                        navigator?.let {}
                    },
                cardColor = Color.White,
                borderColor = Neutral300
            ) {
                if (state.isLoginLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(48.dp),
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
            Spacer(modifier = Modifier.height(24.dp))
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {
                            navigator.navigate(RegisterScreenDestination)
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

            Spacer(modifier = Modifier.height(24.dp))
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {}
                    },
                cardColor = Color.White,
                borderColor = Neutral300
            ) {
                if (state.isLoginLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(48.dp),
                            painter = painterResource(id = R.drawable.ic_newuser),
                            contentDescription = "finger_print",
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = stringResource(R.string.new_user_text_ar),
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
            Spacer(modifier = Modifier.height(16.dp))
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .clickable {
                        navigator?.let {}
                    },
                cardColor = Color.White,
                borderColor = Neutral300
            ) {
                if (state.isLoginLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(48.dp),
                            painter = painterResource(id = R.drawable.ic_newpassword),
                            contentDescription = "finger_print",
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            text = stringResource(R.string.new_password_text),
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
        }


    }

}

@Preview
@Composable
fun DefaultPreview() {
    MarketAppTheme {
        LoginScreen(
            navigator = null
        )
    }
}


