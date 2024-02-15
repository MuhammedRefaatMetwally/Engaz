package com.example.engaz.features.home.view.viewmodels.home

import android.content.Context
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class HomeEvent {


    data class OnMakeOrderClick(val navigator: DestinationsNavigator, val context: Context) : HomeEvent()

    data class OnHomePageInit( val context: Context) : HomeEvent()

}
