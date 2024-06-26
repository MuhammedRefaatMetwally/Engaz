package com.example.engaz.features.home.view.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Lato
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.core.ui.theme.Secondary
import com.example.engaz.core.views.components.shimmerEffect
import io.github.raamcosta.compose_destinations.destinations.EditProfileScreenDestination
import io.github.raamcosta.compose_destinations.destinations.NotificationSettingsScreenDestination
import io.github.raamcosta.compose_destinations.destinations.NotificationsPageDestination
import io.github.raamcosta.compose_destinations.destinations.ProfilePageDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(
    isLoading: Boolean = false,
    profileImage: String,
    username: String,
    wallet: String,
    currency: String,
    navigator: DestinationsNavigator?,
) {
    val context: Context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Card(
            modifier = Modifier
                .size(32.dp)
                .clickable { navigator?.navigate(NotificationsPageDestination) },
            shape = CircleShape,
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            border = BorderStroke(
                1.dp,
                Color.Gray
            )
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp),
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "notification"
            )
        }

        Text(
            text = stringResource(R.string.main_ar),
            fontFamily = Cairo,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )

        Card(
            modifier = Modifier
                .size(32.dp)
                .clickable { navigator?.navigate(ProfilePageDestination) },
            shape = CircleShape,
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            border = BorderStroke(
                1.dp,
                Color.Gray
            )
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 4.dp),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile"
            )
        }

    }
}