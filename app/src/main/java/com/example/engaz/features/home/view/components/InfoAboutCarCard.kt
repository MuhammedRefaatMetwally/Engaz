package com.example.engaz.features.home.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
    isRequest: Boolean = true,
    pendingRequest: Boolean = false,
    showButtons : Boolean = false,
    onAcceptClick: () -> Unit = {},
    onDeclineClick: () -> Unit = {},
    onRequestClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(if (pendingRequest) .5f else .35f)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_car),
                    contentDescription = "car"
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.car_name_ar),
                    fontWeight = FontWeight.W700,
                    fontFamily = Cairo,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "search"
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "رقم اللوحة : ABC 123",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
            Text(
                text = "اللون الأساسى : أحمر",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
            Text(
                text = "الرقم التسلسلى : 18345734845",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
            Text(
                text = "المنصورة",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (pendingRequest) {
                Text(
                    text = "اسم المشترى : ",
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
                Text(
                    text = "الرقم القومى :",
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
                Text(
                    text = "المبلغ المعروض  : ",
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (showButtons) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MainButton(
                            modifier = Modifier
                                .weight(1f)
                                .height(62.dp)
                                .clickable { onAcceptClick() },
                            cardColor = colorResource(id = R.color.primary_color),
                        ) {
                            Text(
                                text = "قبول",
                                style = TextStyle(
                                    fontFamily = Cairo,
                                    fontWeight = FontWeight.W700,
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        MainButton(
                            modifier = Modifier
                                .weight(1f)
                                .height(62.dp)
                                .clickable { onDeclineClick() },
                            cardColor = Color.Red,
                        ) {
                            Text(
                                text = "رفض",
                                style = TextStyle(
                                    fontFamily = Cairo,
                                    fontWeight = FontWeight.W700,
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }
            }

            if (isRequest) {
                MainButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .fillMaxWidth(0.5f)
                        .padding(end = 16.dp)
                        .height(60.dp)
                        .clickable { onRequestClick() },
                    cardColor = colorResource(id = R.color.primary_color),
                ) {
                    Text(
                        text = "إرسال طلب نقل",
                        style = TextStyle(
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}
