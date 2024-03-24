package com.example.engaz.features.notification.views.viewmodel

import android.content.Context
import com.example.engaz.features.auth.view.viewmodels.login.LoginEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class NotificationEvent{
    data class OnBackClick(val navigator: DestinationsNavigator) : NotificationEvent()

    data class OnGetNotification(val context: Context) : NotificationEvent()

}
