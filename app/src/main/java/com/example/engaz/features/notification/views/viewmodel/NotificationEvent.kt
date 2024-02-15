package com.example.engaz.features.notification.views.viewmodel

import android.content.Context

sealed class NotificationEvent{

    data class OnGetNotification(val context: Context) : NotificationEvent()

}
