package com.example.engaz.features.identity.views.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.destinations.FaceRecognitionScreenDestination
import com.example.engaz.features.profile.view.components.BackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun IdentityScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},

    ) {

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(Modifier.fillMaxWidth()) {
            BackButton(onClick = {
                navigator?.let {
                    onBackArrowClick(navigator)
                }
            })
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
            text = "دعنا نتحقق من هويتك",
            fontSize = 20.sp,
            fontFamily = Cairo,
            fontWeight = FontWeight.W700,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
            text = "نحن مطالبون بموجب القانون بالتحقق من هويتك قبل أن نتمكن من استخدام أموالك",
            fontSize = 16.sp,
            fontFamily = Cairo,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))
        Image(painterResource(id = R.drawable.identiy_img), contentDescription = "")
        Spacer(modifier = Modifier.height(80.dp))
        MainButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(64.dp)
                .clickable {
                    navigator?.popBackStack()
                    navigator?.navigate(FaceRecognitionScreenDestination)
                },
            cardColor = colorResource(id = R.color.primary_color)
        ) {
            Text(
                text = "التحقق من الهوية",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}


