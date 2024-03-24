package com.example.engaz.features.home.view.viewmodels.main_info.info_about_service

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class HomeInfoEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : HomeInfoEvent()

}
