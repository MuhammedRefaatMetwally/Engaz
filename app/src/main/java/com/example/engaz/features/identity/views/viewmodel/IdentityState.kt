package com.example.engaz.features.identity.views.viewmodel

import com.example.engaz.features.notification.data.entities.get_all_notification.Notification

data class IdentityState(

    var notifications: List<Notification> = emptyList(),
    var notificationsError: String? = null,
    var notificationsIsLoading: Boolean = false,

    )
