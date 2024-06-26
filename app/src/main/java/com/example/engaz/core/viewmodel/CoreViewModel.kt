package com.example.engaz.core.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.AndroidViewModel
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.domain.usecases.GetUserInfoUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.raamcosta.compose_destinations.destinations.LoginScreenDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import io.github.raamcosta.compose_destinations.destinations.OnBoardingScreenDestination
import io.github.raamcosta.compose_destinations.destinations.SelectLocationScreenDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoreViewModel @Inject constructor(
    val getUserInfoUseCase: GetUserInfoUseCase,
    application: Application,
) : AndroidViewModel(application) {

    var splashScreenId = 0

    companion object {
        private var job: Job? = null
        val scope = CoroutineScope(Dispatchers.Default)


        var user : UserLogin? = null



        val snackbarHostState = SnackbarHostState()
        fun showSnackbar(message : String) {
            job?.cancel()
            job = scope.launch {
                snackbarHostState
                    .showSnackbar(
                        message = message,
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
            }
        }
    }


    private fun initApp() {
        getUserInfo()

    }

     fun getUserInfo(){
        val result = getUserInfoUseCase(getApplication<Application>().applicationContext,splashScreenId)
        if(result.data != null){
            user = result.data
        }
    }

    suspend fun onSplashScreenLaunch(navigator: DestinationsNavigator?) {
        initApp()
        delay(1000)
        Log.d("USER TOKEN", "onSplashScreenLaunch: ${user?.token}")
        if(user != null) {
            navigator?.navigate(MainScreenDestination())

        } else {
           navigator?.navigate(OnBoardingScreenDestination())

        }



    }

    fun onOnBoardingScreenNextClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginScreenDestination())
    }

    fun onOnBoardingScreenSkipClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginScreenDestination())
    }





}