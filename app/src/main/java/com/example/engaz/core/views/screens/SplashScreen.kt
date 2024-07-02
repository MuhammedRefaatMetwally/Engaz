package com.example.engaz.core.views.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.MarketAppTheme
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.views.components.LeftToRightLayout
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@SuppressLint("CoroutineCreationDuringComposition")
@Destination(start = true)
@Composable
fun SplashScreen(
    onScreenLaunch: (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?,
) {
    var visible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        delay(500)
        visible = true
        navigator?.let {
               onScreenLaunch(navigator)
            }
    }
    var playAnimation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        playAnimation = true
    }

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        it
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AnimatedVisibility(
                visible = playAnimation,
                enter = fadeIn(), exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LeftToRightLayout {
                         Image(
                             painter = painterResource(id = R.drawable.logo1), // Provide the resource ID
                             contentDescription = "",
                             modifier = Modifier,
                         )
                     }
                }
            }
        }
    }

}

@Preview
@Composable
fun DefoultSplashScreenPreview() {
    MarketAppTheme {
        SplashScreen(
            navigator = null
        )
    }
}