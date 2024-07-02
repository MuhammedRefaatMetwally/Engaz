package com.example.engaz.features.home.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.ui.theme.Neutral100
import com.example.engaz.core.ui.theme.Neutral900
import com.example.engaz.core.viewmodel.UserPreferences
import com.example.engaz.core.views.components.MainButton
import com.example.engaz.features.auth.view.viewmodels.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun InfoAboutCarCard(
    isRequest: Boolean = true,
    pendingRequest: Boolean = false,
    showButtons : Boolean = false,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onAcceptClick: () -> Unit = {},
    onDeclineClick: () -> Unit = {},
    onRequestClick: () -> Unit = {},
) {
    val context = LocalContext.current
    var loading by remember { mutableStateOf(false) }
    var acceptingLoading by remember { mutableStateOf(false) }
    var refusingLoading by remember { mutableStateOf(false) }
    var user = UserPreferences.getUser(context = context)
    val scope = rememberCoroutineScope()


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(if (pendingRequest) .6f else .35f)
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
                text = "المالك : ${loginViewModel.carDetailsList[0]["nationalId"]}",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )

            Text(
                text = "اللون الأساسى : ${loginViewModel.carDetailsList[0]["color"]}",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
            Text(
                text = " رقم اللوحة :${loginViewModel.carDetailsList[0]["plateNumber"]}",
                fontFamily = Cairo,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )
            Text(
                text = "${loginViewModel.carDetailsList[0]["location"]}",
                fontFamily = Cairo,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (pendingRequest) {
                Text(
                    text = "اسم المشترى : ${user?.username}",
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
                Text(
                    text = " رقم اللوحة : ق ع ف 7 5 3",
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                )
                Text(
                    text = "المبلغ المعروض  : 300.000 جتيه",
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
                        Button(
                            onClick = {
                                acceptingLoading = true
                                scope.launch {
                                    delay(2000)
                                    acceptingLoading = false
                                    onAcceptClick()
                                }
                                      },
                            modifier  = Modifier
                                .weight(1f)
                                .height(62.dp)
                                .clickable { acceptingLoading = true
                                    scope.launch {
                                        delay(2000)
                                        loading = false
                                        onDeclineClick()
                                    }},
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_color))
                        ) {
                            if(loading){
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(28.dp),color = Color.White)
                                }
                            }else{
                                Text(
                                    text = "قبول",
                                    style = TextStyle(
                                        fontFamily = Cairo,
                                        fontWeight = FontWeight.W700,
                                        color = Color.White ,
                                        fontSize = 18.sp
                                    )
                                )
                            }

                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = { onDeclineClick() },
                            modifier  = Modifier
                                .weight(1f)
                                .height(62.dp)
                                .clickable { onDeclineClick() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            if(loading){
                                Box(
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(modifier = Modifier.size(28.dp),color = Color.White)
                                }
                            }else{
                                Text(
                                    text = "رفض",
                                    color = Color.White ,
                                    fontFamily = Cairo,
                                    fontWeight = FontWeight.W700,
                                    fontSize = 18.sp
                                )
                            }

                        }
                    }
                }
            }

            if (isRequest) {
                Button(
                    onClick = {
                        loading = true
                        scope.launch {
                            delay(1000)
                            loading = false
                            onRequestClick()
                        }
                              },
                    modifier = Modifier
                        .align(Alignment.End)
                        .fillMaxWidth(0.6f)
                        .padding(end = 16.dp)
                        .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary_color))
                ) {
                    if(loading){
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(28.dp),color = Color.White)
                        }
                    }else{
                        Text(
                            text = "إرسال طلب نقل",
                            color = Color.White,
                            fontFamily = Cairo,
                            fontWeight = FontWeight.W700,
                            fontSize = 14.sp
                        )
                    }

                }
            }
        }
    }
}
