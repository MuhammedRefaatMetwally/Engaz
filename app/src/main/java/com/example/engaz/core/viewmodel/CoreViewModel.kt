package com.example.engaz.core.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.domain.usecases.GetUserInfoUseCase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.raamcosta.compose_destinations.destinations.LoginScreenDestination
import io.github.raamcosta.compose_destinations.destinations.OnBoardingScreenDestination
import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.RequestError
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
    private val _user = MutableLiveData<UserLogin?>()
    val user2: LiveData<UserLogin?> get() = _user

    init { loadUserData() }

    private fun loadUserData() {
        _user.value = UserPreferences.getUser(getApplication<Application>().applicationContext)
        UserManager.user = UserPreferences.getUser(getApplication<Application>().applicationContext)

    }

    fun updateUser(user: UserLogin?) {
        _user.value = user
        user?.let {
            UserPreferences.saveUser(getApplication<Application>().applicationContext, it)
        } ?: run {
            UserPreferences.clearUser(getApplication<Application>().applicationContext)
        }
    }


    companion object {
        private var job: Job? = null
        val scope = CoroutineScope(Dispatchers.Default)


        var user : UserLogin? = null
        var userAddress : String? = null


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

     private fun getUserInfo(){
        val result = getUserInfoUseCase(getApplication<Application>().applicationContext,splashScreenId)
         val userAddressResult = UserPreferences.getUserPrivateAddress(context = getApplication<Application>().applicationContext)
        if(result.data != null){
            user = result.data
            UserManager.user =result.data
        }
         userAddress = userAddressResult
    }

    suspend fun onSplashScreenLaunch(navigator: DestinationsNavigator?) {
        initApp()
        delay(1000)
        Log.d("USER TOKEN", "onSplashScreenLaunch: ${user?.token}")
       if(userAddress != null){
           if(user != null) {
               navigator?.popBackStack()
               navigator?.navigate(LoginScreenDestination())
               Log.d("PublicKey",
                   "onSplashScreenLaunch: ${UserPreferences.getUserPublicAddress(getApplication<Application>().applicationContext)}")

           } else {
               navigator?.popBackStack()
               navigator?.navigate(OnBoardingScreenDestination())

           }
       }else{
           val ethereum = Ethereum(getApplication<Application>().applicationContext)

           val dapp = Dapp("Droid Dapp", "https://droiddapp.com")

           // This is the same as calling eth_requestAccounts.
           val result = ethereum.connect(dapp) { result ->
               if (result is RequestError) {
                   Log.e("eth", "Ethereum connection error: ${result.message}")
               } else {
                   UserPreferences.saveUserAddress(getApplication<Application>().applicationContext,result.toString())
                   Log.d("eth2", "Ethereum connection result: $result")
                   navigator?.navigate(LoginScreenDestination)
               }
           }
           UserPreferences.saveUserAddress(getApplication<Application>().applicationContext,result.toString())
       }




    }

    fun onOnBoardingScreenNextClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginScreenDestination())
    }

    fun onOnBoardingScreenSkipClick(navigator: DestinationsNavigator?) {
        navigator?.navigate(LoginScreenDestination())
    }





}