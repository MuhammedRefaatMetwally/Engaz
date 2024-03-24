package com.example.engaz.features.identity.views.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.engaz.features.notification.views.viewmodel.NotificationState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
class IdentityViewModel @Inject constructor(

): ViewModel() {


    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    fun onEvent(event : IdentityEvent){
        when(event){
            is IdentityEvent.OnBackClick -> onBackClick(navigator = event.navigator)
        }
    }



}