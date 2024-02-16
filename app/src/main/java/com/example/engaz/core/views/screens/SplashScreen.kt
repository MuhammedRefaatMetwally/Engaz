package com.example.engaz.core.views.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.MarketAppTheme
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.ui.theme.linearGradient
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun SplashScreen(
    onScreenLaunch: (DestinationsNavigator) -> Unit = {},
    navigator: DestinationsNavigator?
) {

    var visible by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    /*LaunchedEffect(key1 = true) {
        delay(500)
        visible = true
        navigator?.let {
                onScreenLaunch(navigator)
            }
    }*/

    var playAnimation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        playAnimation = true
    }

    AnimatedVisibility(
        visible = playAnimation,
        enter = scaleIn(tween(3000)),
        exit = scaleOut(tween(3000))
    ) {
        LogoBackgroundAnimation()
    }
    AnimatedVisibility(visible = playAnimation, enter = fadeIn(), exit = fadeOut()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.engaz_logo2), // Provide the resource ID
                contentDescription = "",
                modifier = Modifier,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.engaz_text_ar),
                fontWeight = FontWeight.W700,
                fontSize = 80.sp,
                fontFamily = Cairo,
                color = colorResource(id = R.color.primary_color)
            )

        }
    }

    if (playAnimation) {
        LaunchedEffect(Unit) {
            delay(1500)
            navigator?.let {
                onScreenLaunch(navigator)
            }
        }
    }

   /* Scaffold(
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

                    Image(
                        painter = painterResource(id = R.drawable.engaz_logo2), // Provide the resource ID
                        contentDescription = "",
                        modifier = Modifier,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.engaz_text_ar),
                        fontWeight = FontWeight.W700,
                        fontSize = 80.sp,
                        fontFamily = Cairo,
                        color = colorResource(id = R.color.primary_color)
                    )

                }
            }


        }

    }*/

}

@Composable
fun LogoBackgroundAnimation() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(7F, 7F) {
            drawCircle(linearGradient)
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