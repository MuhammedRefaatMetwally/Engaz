package com.example.engaz.core.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.raamcosta.compose_destinations.destinations.HomePageDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.ProfilePageDestination

@Composable
fun CustomDialog(
    processText : String ,
    buttonText : String,
    navigator: DestinationsNavigator?,
    setShowDialog: (Boolean) -> Unit,
) {


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LeftToRightLayout {
                    Image(

                        painter = painterResource(id = R.drawable.ic_approve),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "عميلنا العزيز",
                    fontSize = 30.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = 20.sp,
                                fontFamily = Cairo,
                                fontWeight = FontWeight.W400, color = Color.Gray
                            )
                        ) {
                            append(processText)
                        }
                        withStyle(SpanStyle()){
                            append(" ")
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = 20.sp,
                                fontFamily = Cairo,
                                fontWeight = FontWeight.W700, color = Color.Green
                            )
                        ) {
                            append("بنجاح!")
                        }
                    }
                )
                Text(
                    text = "رقم الطلب : 15",
                    fontSize = 20.sp,
                    fontFamily = Cairo,
                    fontWeight = FontWeight.W400, color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp))
                MainButton(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(64.dp)
                        .clickable {
                            navigator?.popBackStack()
                            navigator?.navigate(MainScreenDestination)
                        },
                    cardColor = colorResource(id = R.color.primary_color)
                ) {
                    Text(
                        text = buttonText,
                        fontFamily = Cairo,
                        fontWeight = FontWeight.W700,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}