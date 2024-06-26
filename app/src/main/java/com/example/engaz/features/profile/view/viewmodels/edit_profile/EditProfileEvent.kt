package com.example.engaz.features.profile.view.viewmodels.edit_profile

import android.content.Context
import com.example.engaz.features.notification.views.viewmodel.NotificationEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class EditProfileEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : EditProfileEvent()
    data class OnLogOut(val navigator: DestinationsNavigator,val context: Context) : EditProfileEvent()

    class OnSave(val navigator: DestinationsNavigator,val context: Context) : EditProfileEvent()


}
