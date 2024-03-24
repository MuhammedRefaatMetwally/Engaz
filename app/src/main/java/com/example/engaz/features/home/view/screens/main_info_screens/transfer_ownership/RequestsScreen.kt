package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.engaz.destinations.AcceptedRequestDetailsDestination
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.example.engaz.features.profile.view.components.BackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun RequestsScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth()) {
            BackButton(onClick = {
                navigator?.let {
                    onBackArrowClick(navigator)
                }
            })

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = stringResource(R.string.sent_requests_ar),
                fontSize = 20.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        InfoAboutCarCard(
            isRequest = false,
            pendingRequest = true,
            showButtons = true,
            onAcceptClick = {
               navigator?.navigate(AcceptedRequestDetailsDestination)
            })
    }
}