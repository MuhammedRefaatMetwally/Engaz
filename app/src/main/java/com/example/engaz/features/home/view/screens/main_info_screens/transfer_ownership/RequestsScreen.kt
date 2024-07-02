package com.example.engaz.features.home.view.screens.main_info_screens.transfer_ownership

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.viewmodel.UserPreferences
import io.github.raamcosta.compose_destinations.destinations.AcceptedRequestDetailsDestination
import com.example.engaz.features.home.view.components.InfoAboutCarCard
import com.example.engaz.features.profile.view.components.BackButton
import com.example.engaz.features.profile.view.components.Header
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import org.eclipse.jetty.server.Authentication.User

@Composable
@Destination
fun RequestsScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    var loading by remember { mutableStateOf(true) }

    // Simulate loading delay
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay for loading simulation
        loading = false
    }
    val context = LocalContext.current
    val user = UserPreferences.getUser(context)
    val userPrefsPrivate = UserPreferences.getUserPrivateAddress(context)
    val userAddress = "0xeb3589dc2ead3faef004c102fbb1ae5b3b32b20a"
    if (loading) {
        // Show loading indicator
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primary_color))
        }
    } else {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                Header(label = stringResource(R.string.sent_requests_ar)) {
                    navigator?.let {
                        onBackArrowClick(navigator)
                    }
                }

            }
            Spacer(modifier = Modifier.height(24.dp))

            if (user?.username != "muhammedrefaat") {
                InfoAboutCarCard(
                    isRequest = false,
                    pendingRequest = true,
                    showButtons = true,
                    onAcceptClick = {
                        navigator?.navigate(AcceptedRequestDetailsDestination)
                    })
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "لا يوجد طلبات", fontSize = 24.sp, fontWeight = FontWeight.W700,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}