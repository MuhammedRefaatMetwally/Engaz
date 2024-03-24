package com.example.engaz.features.identity.views.viewmodel

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class IdentityEvent{
    data class OnBackClick(val navigator: DestinationsNavigator) : IdentityEvent()


}
