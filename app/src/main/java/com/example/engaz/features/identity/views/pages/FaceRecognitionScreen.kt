package com.example.engaz.features.identity.views.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Primary
import com.example.engaz.core.ui.theme.linearGradient
import com.example.engaz.features.profile.view.components.BackButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun FaceRecognitionScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Box(
        modifier = Modifier.background(
            Brush.linearGradient(
                0.0f to Primary,
                500.0f to Primary,
                1.0f to Color.Black,
                start = Offset.Zero,
                end = Offset.Infinite
            )
        )
    ) {
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(16.dp))
                Surface(
                    Modifier
                        .size(40.dp)
                        .clickable { navigator?.popBackStack() },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.White),
                    color = Color.Transparent
                ) {
                    Icon(modifier = Modifier.padding(8.dp),imageVector = Icons.Default.Close, contentDescription = "", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(72.dp))
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "ركز على وجهك",
                    fontSize = 20.sp,
                    fontFamily = Cairo,
                    color = Color.White,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Image(
                modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally).padding(16.dp),
                painter = painterResource(id = R.drawable.face_reco_img),
                contentDescription = "",
            )

        }
    }
}