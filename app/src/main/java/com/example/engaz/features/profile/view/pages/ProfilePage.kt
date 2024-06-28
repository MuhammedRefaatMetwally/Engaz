package com.example.engaz.features.profile.view.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.features.profile.view.components.ProfileItem
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.viewmodel.CoreViewModel
import io.github.raamcosta.compose_destinations.destinations.EditProfileScreenDestination
import io.github.raamcosta.compose_destinations.destinations.LanguageScreenDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.NotificationsPageDestination
import com.example.engaz.features.profile.view.components.Header
import com.example.engaz.features.profile.view.components.LogoutAlertDialog
import com.example.engaz.features.profile.view.viewmodels.edit_profile.EditProfileState
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun ProfilePage(
    coreViewModel: CoreViewModel = hiltViewModel(),
    profileState: EditProfileState = EditProfileState(),
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
    onLogOut :(DestinationsNavigator, Context) -> Unit= { _, _ -> },
    navigator: DestinationsNavigator?,
) {
    val context: Context = LocalContext.current
    val scroll = rememberScrollState()
    var showDialog by remember { mutableStateOf(false)}
    val user by coreViewModel.user2.observeAsState()
    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            if(navigator !=null){
                Header(
                    label = "",
                    onClick = {
                        navigator?.let {
                            navigator.navigate(MainScreenDestination)
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(132.dp),
                shape = CircleShape,
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Image(painter = painterResource(id = R.drawable.profile), contentDescription = "")
            }
            Text(
                text = CoreViewModel.user?.username?:"",
                fontFamily = Cairo,
                fontSize = 32.sp,
                fontWeight = FontWeight.W700,
            )

            Text(
                text = CoreViewModel.user?.email?:"",
                fontSize = 16.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W400
            )
            Divider(Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
            ProfileItem(
                icon = R.drawable.ic_profie2,
                leadingLabel = stringResource(R.string.edit_profile_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(EditProfileScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.notification,
                leadingLabel = stringResource(R.string.notifications_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(NotificationsPageDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_payment,
                leadingLabel = stringResource(R.string.payment_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_dark_mode,
                leadingLabel = stringResource(R.string.dark_mode_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.lock_inactive,
                leadingLabel = stringResource(R.string.privacy_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_help,
                leadingLabel = stringResource(R.string.help_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_settings,
                leadingLabel = stringResource(R.string.settings),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_language,
                leadingLabel = stringResource(R.string.language_ar),
                onClick = {
                    navigator?.let {
                        navigator.navigate(LanguageScreenDestination)
                    }
                },
            )
            ProfileItem(
                icon = R.drawable.ic_logout,
                leadingLabel = stringResource(R.string.logout_ar2),
                onClick = {
                    showDialog = true
                },
            )

            Spacer(modifier = Modifier.height(8.dp))
            if (showDialog) {
                LogoutAlertDialog(onDismiss = { showDialog = false }, onConfirm = {
                    showDialog = false
                    if (navigator != null) {
                            onLogOut(navigator,context)
                    }
                })
            }
        }
    }


}


