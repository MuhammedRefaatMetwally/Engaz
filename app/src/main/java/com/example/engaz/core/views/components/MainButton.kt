package com.example.engaz.core.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral500

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    cardColor: Color = Neutral500,
    borderColor: Color = Neutral500,
    content: @Composable ()-> Unit = {}
    ){

    Card(
        modifier,
        shape = CircleShape,
        border = BorderStroke(1.5.dp, color = borderColor),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        ),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            content()
        }
    }

}


@Preview
@Composable
fun MainButtonPreview(){
    MainButton(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            .height(20.dp)
    ){


        androidx.compose.material.Text(
            modifier = Modifier,
            text = "إرسال طلب نقل",
            style = TextStyle(
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                color = Color.White,
                fontSize = 14.sp,
            )
        )

    }
}