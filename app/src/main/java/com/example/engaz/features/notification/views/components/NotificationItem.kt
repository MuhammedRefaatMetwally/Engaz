package com.example.engaz.features.notification.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.core.views.components.LeftToRightLayout
import com.example.engaz.features.notification.data.entities.get_all_notification.Notification

@Composable
fun NotificationItem(
    icon: Int,
    mainTitle: String,
    secondTitle: String,
    isNew: Boolean = false
) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

        LeftToRightLayout {
            Image(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = icon),
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Column {
           Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = mainTitle,
                fontSize = 18.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
            )
            Text(
                text = secondTitle,
                fontSize = 14.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                color = Color.Gray
            )


            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "20 ديسمبر 2023 | 20:33 م", fontSize = 14.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    color = Color.Gray
                )
                if (isNew) {
                    Surface(
                        Modifier
                            .width(60.dp)
                            .height(24.dp)
                            .align(Alignment.Bottom), shape = RoundedCornerShape(16.dp),
                        color = Primary
                    ) {
                        Text(
                            text = stringResource(R.string.new_ar),
                            color = Color.White, fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }


        }

    }

}