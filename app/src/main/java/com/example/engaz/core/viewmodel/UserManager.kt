package com.example.engaz.core.viewmodel


import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import com.example.engaz.features.auth.data.entities.login.UserLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object UserManager {
    var user: UserLogin? = null

    // Snackbar related properties
    private var job: Job? = null
    val snackbarHostState = SnackbarHostState()

    val scope = CoroutineScope(Dispatchers.Default)

    fun showSnackbar(message: String) {
        job?.cancel()
        job = scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
        }
    }
}
