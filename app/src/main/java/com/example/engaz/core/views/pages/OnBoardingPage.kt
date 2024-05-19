package com.example.engaz.core.views.pages
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Lato
import kotlinx.coroutines.delay

@Composable
fun OnBoardingPage(
    label: String = "",
    subText: String = "",
    image: Int = 0,
) {
    var visible1 by remember { mutableStateOf(false) }
    var visible2 by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    LaunchedEffect(true) {
        delay(100)
        visible1 = true
        delay(100)
        visible2 = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f), // Adjust weight as needed
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 248.dp)
            )
        }



        AnimatedVisibility(
            visible = visible1,
            enter = slideInHorizontally { with(density) { 20.dp.roundToPx() } } + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontFamily = Cairo,
                    color = colorResource(id = R.color.primary_color),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(
            visible = visible2,
            enter = slideInHorizontally { with(density) { 40.dp.roundToPx() } } + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                text = subText,
                style = TextStyle(
                    fontFamily = Cairo,
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}


