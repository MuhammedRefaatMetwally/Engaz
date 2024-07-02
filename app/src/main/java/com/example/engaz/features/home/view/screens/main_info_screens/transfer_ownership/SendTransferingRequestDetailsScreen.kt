package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.views.components.CustomDialog
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.features.auth.view.viewmodels.login.LoginViewModel
import io.github.raamcosta.compose_destinations.destinations.RequestsScreenDestination
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.example.engaz.features.profile.view.components.Header
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
@Destination
fun SendTransferingRequestDetailsScreen(
    navigator: DestinationsNavigator?,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val showDialog = remember { mutableStateOf(false) }
    if(showDialog.value)
    CustomDialog(
        processText = "تمت ارسال طلب الملكية",
        buttonText = "الرئيسة",
        navigator = navigator,
        setShowDialog = {
            showDialog.value = it
        })

    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth()) {
            Header(label = stringResource(R.string.request_ownership_details)) {
                navigator?.let {
                    onBackArrowClick(navigator)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.car_info_ar),
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            fontFamily = Cairo,
            modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
        )

        InfoAboutCarCard(false) {
            // Your callback here
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.info_about_current_owner_ar),
            fontWeight = FontWeight.W700,
            fontSize = 16.sp,
            fontFamily = Cairo,
            modifier = Modifier.padding(start = 16.dp, bottom = 4.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(80.dp),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "الاسم : ${loginViewModel.carDetailsList[0]["nationalId"]} ",
                        fontFamily = Cairo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "المكان : ${loginViewModel.carDetailsList[0]["location"]}",
                        fontFamily = Cairo,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {
                    loading = true
                    scope.launch {
                        delay(2000)
                        loading = false
                        showDialog.value = true
                    }
                },
            cardColor = colorResource(id = R.color.primary_color)
        ) {
            if(loading){
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }else{
                Text(
                    text = stringResource(R.string.send_transfering_ownership_request_ar),
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }

        }
    }
}