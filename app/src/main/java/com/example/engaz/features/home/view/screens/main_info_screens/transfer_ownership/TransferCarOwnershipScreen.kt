package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.destinations.SendTransferingRequestScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TransferCarOwnershipScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        if(navigator != null){
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
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.enter_car_number),
            fontSize = 30.sp,
            fontWeight = FontWeight.W700,
            fontFamily = Cairo
        )
        Text(modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.please_enter_car_number_ar),
            fontFamily = Cairo,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = stringResource(R.string.car_number_ar),
            placeHolder = "ABC123",

            )

        Spacer(modifier = Modifier.height(48.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp).clickable {
                  navigator?.navigate(SendTransferingRequestScreenDestination)
                },
            cardColor = colorResource(id = R.color.primary_color)
        ){
            Text(
                text = stringResource(R.string.search_ar),
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}