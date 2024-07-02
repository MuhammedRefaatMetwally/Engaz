package com.example.engaz.features.profile.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarDetailsCard(carDetails: Map<String, Any>, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = carDetails["image"] as Int), // Replace with your car image resource
                contentDescription = "",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "المالك: ${carDetails["nationalId"]}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "رقم اللوحة: ${carDetails["plateNumber"]}",
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "اللون: ${carDetails["color"]}",
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "المكان: ${carDetails["location"]}",
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral900,
                )
            }
        }
    }
}
