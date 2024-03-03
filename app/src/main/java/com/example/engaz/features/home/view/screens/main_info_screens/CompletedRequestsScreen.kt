package com.example.engaz.features.home.view.screens.main_info_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun CompletedRequestsScreen(
    navigator: DestinationsNavigator?,
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},
) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        Row(Modifier.fillMaxWidth()) {
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

            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                text = stringResource(R.string.completed_request),
                fontSize = 20.sp,
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row {
            Surface(
                Modifier.size(80.dp).padding(8.dp),
                shape = CircleShape,
                color = colorResource(id = R.color.bg_primary_color)
            ) {
                Icon(modifier = Modifier.padding(16.dp),
                    painter = painterResource(id = R.drawable.ic_comp_transfer2),
                    contentDescription = "",
                    tint = Primary
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                    text = stringResource(R.string.approved_transfering_ar),
                    fontSize = 18.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = stringResource(R.string.approved_transfering_desc_ar),
                    fontSize = 14.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    color = Color.Gray
                )

                Text(
                    text = "20 ديسمبر 2023 | 20:33 م", fontSize = 14.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700,
                    color = Color.Gray
                )
            }
        }
    }
}