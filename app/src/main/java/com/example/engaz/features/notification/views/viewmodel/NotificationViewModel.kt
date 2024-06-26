package com.example.engaz.features.notification.views.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.notification.domain.usecase.GetAllNotificationsUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAllNotificationsUseCase : GetAllNotificationsUseCase,
): ViewModel() {

    var state by mutableStateOf(NotificationState())
    private var job : Job = Job()

    private fun onGetNotification(context: Context) {
        /*job = viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(notificationsError = null)

            state = state.copy(notificationsIsLoading = true)

            val response = CoreViewModel.user!!.token?.let {
                getAllNotificationsUseCase(
                    it,
                    context = context
                )
            }

            state = state.copy(notificationsIsLoading = false)

            if(response.failure != null) {
                state = state.copy(notificationsError = response.failure.message)
            }else {
                state = state.copy(notifications = response.data!!.data.notifications)
            }
        }*/
    }
    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    fun onEvent(event : NotificationEvent){
        when(event){
            is NotificationEvent.OnGetNotification -> {
                onGetNotification(event.context)
            }

            is NotificationEvent.OnBackClick -> onBackClick(navigator = event.navigator)
        }
    }



}