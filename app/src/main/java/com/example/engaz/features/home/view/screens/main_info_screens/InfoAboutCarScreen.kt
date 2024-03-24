package com.example.engaz.features.home.view.screens.main_info_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.views.components.CustomTextField
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun InfoAboutCarScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth()) {
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

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = stringResource(R.string.infor_about_car),
                fontSize = 20.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        CustomTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            leadingIcon = {
                Icon(modifier = Modifier.size(32.dp), imageVector = Icons.Default.Search, contentDescription = "search", tint = Color.Black)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        InfoAboutCarCard(pendingRequest = true, isRequest = false){

        }

    }
}