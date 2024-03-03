package com.example.engaz.features.home.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.MainButton

@Composable
fun InfoAboutCarCard(
    noRequest: Boolean = true,
    onClick: () -> Unit = {},
) {
    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(.35f)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_car),
                contentDescription = "car"
            )
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                text = stringResource(R.string.car_name_ar),
                fontWeight = FontWeight.W700,
                fontFamily = Cairo,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.width(170.dp))
            Icon(
                modifier = Modifier.padding(top = 16.dp),
                imageVector = Icons.Default.MoreVert,
                contentDescription = "search"
            )
        }
        Divider(color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "رقم اللوحة : ABC 123", fontFamily = Cairo, fontWeight = FontWeight.W400,
            fontSize = 14.sp,
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "اللون الأساسى : أحمر", fontFamily = Cairo, fontWeight = FontWeight.W400,
            fontSize = 14.sp,
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "الرقم التسلسلى : 18345734845",
            fontFamily = Cairo,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "المنصورة", fontFamily = Cairo, fontWeight = FontWeight.W700,
            fontSize = 16.sp,
        )
          if(noRequest){
              MainButton(
                  Modifier
                      .align(Alignment.End)
                      .fillMaxWidth(.5f)
                      .padding(end = 16.dp)
                      .height(60.dp)
                      .clickable { onClick() },
                  cardColor = colorResource(id = R.color.primary_color),
              ) {
                  Text(
                      modifier = Modifier,
                      text = "إرسال طلب نقل",
                      style = TextStyle(
                          fontFamily = Cairo,
                          fontWeight = FontWeight.W700,
                          color = Color.White,
                          fontSize = 14.sp,
                      )
                  )
              }
          }

    }
}