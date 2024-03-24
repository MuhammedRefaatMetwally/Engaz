package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.features.auth.view.viewmodels.login.LoginState
import com.example.engaz.features.home.view.viewmodels.main_info.complete_payment.CompletePaymentState
import com.example.engaz.features.profile.view.components.BackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun CompletePaymentScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onChangeCVC: (String) -> Unit = {},
    state: CompletePaymentState = CompletePaymentState(),
    onSecureCVCClick: () -> Unit = {},
) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        CustomDialog(
            processText = "تمت عملية الدفع",
            buttonText = "إرسال إيصال",
            navigator = navigator,
            setShowDialog = {
                showDialog.value = it
            })

    Column {
        Spacer(modifier = Modifier.height(24.dp))
        BackButton(onClick = {
            navigator?.let {
                onBackArrowClick(navigator)
            }
        })

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.visa_img),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(32.dp))
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = "اسم البطاقة",
            trailingIcon = {
                Icon(Icons.Default.Person3, contentDescription = "")
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = "رقم البطاقة",
            trailingIcon = {
                Icon(Icons.Default.Payment, contentDescription = "")
            }
        )
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = "تاريخ الانتهاء",
            trailingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "")
            }
        )

        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = "CVC",
            value = state.cvc,
            isSecure = state.isCVCSecure,
            onValueChange = {
                onChangeCVC(it)
            },
            trailingIcon = {
                Image(
                    modifier = Modifier
                        .padding(end = 0.dp)
                        .clickable {
                            onSecureCVCClick()
                        },
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = ""
                )
            }
        )

        Spacer(modifier = Modifier.height(40.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {
                    showDialog.value = true
                },
            cardColor = colorResource(id = R.color.primary_color)
        ) {
            Text(
                text = "إتمام الدفع",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {

                },
            cardColor = colorResource(id = R.color.white)
        ) {
            Text(
                text = "إلغاء",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.Black,
                fontSize = 20.sp
            )
        }


    }
}