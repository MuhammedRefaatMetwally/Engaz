package com.example.engaz.features.home.view.screens.main_info_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.ui.theme.Primary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun InfoAboutServiceScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    val steps = listOf(
        stringResource(R.string.step1),
        stringResource(R.string.step2),
        stringResource(R.string.step3),
        stringResource(R.string.step4),
        stringResource(R.string.step5),
    )
    val serviceSteps = listOf(
        stringResource(R.string.step1_desc),
        stringResource(R.string.step2_desc),
        stringResource(R.string.step3_desc),
        stringResource(R.string.step4_desc),
        stringResource(R.string.confirm_paying_ar)
    )
    val serviceImages = listOf(
        R.drawable.ic_step5,
        R.drawable.ic_step4,
        R.drawable.ic_step3,
        R.drawable.ic_step2,
        R.drawable.ic_step1,

    )
    Column {
        Spacer(modifier = Modifier.height(20.dp))

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

        Text(modifier = Modifier.padding(start = 16.dp, top = 16.dp),text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    fontSize = 25.sp
                )
            ) {
                append(stringResource(R.string.welcome_to_info_service_ar))
            }

            withStyle(SpanStyle()){
                append(" ")
            }

            withStyle(
                SpanStyle(
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    fontSize = 25.sp,
                    color = colorResource(id = R.color.primary_color)
                )
            ) {
                append(stringResource(R.string.engaz_ar))
            }
        })

        Spacer(modifier = Modifier.height(16.dp))
        Text(modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.info_service_description_ar),
            fontFamily = Cairo,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.service_steps_ar),
            fontFamily = Cairo,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(Modifier.fillMaxWidth()){
            items(serviceSteps.size){
                Card(
                    modifier = Modifier
                        .fillMaxHeight(.8f)
                        .width(255.dp)
                        .weight(.5f)
                        .padding(horizontal = 8.dp), shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Primary),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Text(modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 24.dp),
                        text = steps[it], fontFamily = Cairo,
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        painter = painterResource(id = serviceImages[it]),
                        contentDescription = "step1"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(modifier = Modifier.padding(horizontal = 16.dp),
                        text = serviceSteps[it], fontFamily = Cairo,
                        fontWeight = FontWeight.W400,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}