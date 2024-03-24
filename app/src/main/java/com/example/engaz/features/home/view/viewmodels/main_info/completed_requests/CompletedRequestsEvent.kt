package com.example.engaz.features.home.view.viewmodels.main_info.completed_requests

import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class CompletedRequestsEvent {
    data class OnBackClick(val navigator: DestinationsNavigator) : CompletedRequestsEvent()

}
