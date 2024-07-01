package com.example.engaz.features.notification.views.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.features.notification.data.entities.get_all_notification.Notification
import com.example.engaz.features.notification.views.components.NotificationItem
import com.example.engaz.features.notification.views.viewmodel.NotificationState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun NotificationsPage(
    navigator: DestinationsNavigator?,
    notificationState: NotificationState = NotificationState(),
    getNotification: () -> Unit = {},
    onBackArrowClick: (DestinationsNavigator) -> Unit = {},

    ) {

    val notificationsMap = mutableMapOf<String, List<Notification>>()

    LaunchedEffect(key1 = true) {
        getNotification()
    }

    notificationState.notifications.forEach {
        if (!notificationsMap.containsKey(it.created_at)) {
            notificationsMap[it.created_at] = mutableListOf(it)
        } else {
            notificationsMap[it.created_at] = notificationsMap[it.created_at]!! + it
        }
    }

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(30.dp)) }
            item { Row(Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp)
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
                Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {

                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(R.string.notifications),
                            style = TextStyle(
                                fontFamily = Cairo,
                                color = Color.Black,
                                fontSize = 24.sp
                            )
                        )
                    }
                }


            }

            item {
                Spacer(modifier = Modifier.height(25.dp))

            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                NotificationItem(
                    icon = R.drawable.ic_cancel,
                    isNew = true,
                    mainTitle = "تم إلغاء طلب نقل الملكية!",
                    secondTitle = "تم إلغاء طلبك."
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                NotificationItem(
                    icon = R.drawable.ic_accepted,
                    isNew = true,
                    mainTitle = "تم قبول طلب نقل الملكية!",
                    secondTitle = "تم إرسال طلبك بنجاح , يمكنك الان تحميل عقد نقل الملكية."
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                NotificationItem(
                    icon = R.drawable.ic_payment_notifi,
                    mainTitle = "تم الاتصال ببطاقتك الائتمانيه!",
                    secondTitle = "تم ربط بطاقتك الائتمانيه بالابلكيشن , استعلم بخدماتها."
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                NotificationItem(
                    icon = R.drawable.ic_confirm,
                    mainTitle = "تأكيد الدفع",
                    secondTitle = "تم استلام المبلغ بنجاح , يمكنك الان تحويل ملكية السيارة."
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                NotificationItem(
                    icon = R.drawable.ic_update,
                    mainTitle = "تم تحديث حالة طلب نقل الملكية",
                    secondTitle = "طلبك مقبول."
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                NotificationItem(
                    icon = R.drawable.ic_created,
                    mainTitle = "تم إنشاء حسابك!",
                    secondTitle = "تم إنشاء حسابك بنجاح , يمكنك الان التمتع بخدمة نقل ملكية السيارة."
                )
            }
        }


    }
}


@Preview
@Composable
fun NotificationsPagePreview() {
    NotificationsPage(navigator = null)
}