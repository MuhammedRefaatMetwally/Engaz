package com.example.engaz.features.profile.view.viewmodels.edit_profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.auth.domain.usecases.LogoutUseCase
import com.example.engaz.features.auth.domain.usecases.SaveUserInfoUseCase
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import com.example.engaz.features.profile.domain.usecase.UpdatePhoneNumberStep2Usecase
import com.example.engaz.features.profile.domain.usecase.UpdatePhoneNumberUsecase
import com.example.engaz.features.profile.domain.usecase.UpdateProfileNameAndImageUsecase
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.raamcosta.compose_destinations.destinations.LoginScreenDestination
import io.github.raamcosta.compose_destinations.destinations.MainScreenDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updatePhoneNumberUsecase: UpdatePhoneNumberUsecase,
    private val updatePhoneNumberStep2Usecase: UpdatePhoneNumberStep2Usecase,
    private val logoutUseCase: LogoutUseCase,
    private val updateProfileNameAndImageUsecase: UpdateProfileNameAndImageUsecase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) : ViewModel() {

    var state: EditProfileState by mutableStateOf(EditProfileState())
    private var job: Job? = null

    fun updateUsername(username: String) {
        state = state.copy(
            usernameTextField = username
        )
    }

    fun updatePhone(phone: String) {
        state = state.copy(
            phoneTextField = phone
        )
    }

    fun updateProfileImage(image: Uri?) {

        state = state.copy(
            pickedProfileImage = image
        )


    }

    private fun onLogout(navigator: DestinationsNavigator, context: Context) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {

            val response = logoutUseCase(
                context = context,
            )


            if (response.failure != null) {
                CoreViewModel.showSnackbar(("Error:" + response.failure.message))
            } else {
                CoreViewModel.showSnackbar(("Success:" + response.data?.message))
                viewModelScope.launch(Dispatchers.Main) {
                    navigator.popBackStack()
                    navigator.navigate(LoginScreenDestination())
                }

            }

        }
    }

    private fun onSave(navigator: DestinationsNavigator, context: Context) {
        if (job == null) {
            job = viewModelScope.launch(Dispatchers.IO) {

                state = state.copy(
                    profileError = null,
                    profileIsLoading = true
                )

                val response = if (state.usernameTextField.isBlank()) {

                    CoreViewModel.user!!.token?.let {
                        updateProfileNameAndImageUsecase(
                            it,
                            CoreViewModel.user!!.username,
                            state.pickedProfileImage,
                            context
                        )
                    }

                } else {

                    CoreViewModel.user!!.token?.let {
                        updateProfileNameAndImageUsecase(
                            it,
                            state.usernameTextField,
                            state.pickedProfileImage,
                            context
                        )
                    }
                }

                if (response?.failure != null) {
                    state = state.copy(
                        profileError = response.failure.message
                    )
                    CoreViewModel.showSnackbar(("Error:" + response.failure.message))

                    Log.v("CoreViewModel", response.failure.message)

                } else {

                    saveUserInfoUseCase(
                        CoreViewModel.user!!.copy(
                            imageUrl = response?.data!!.data.user.image,
                            username = response.data.data.user.fullname,

                            ),
                        context,
                        0
                    )

                    CoreViewModel.user = CoreViewModel.user!!.copy(
                        imageUrl = response.data.data.user.image,
                        username = response.data.data.user.fullname,
                    )

                    state = state.copy(
                        profileImage = response.data.data.user.image,
                        username = response.data.data.user.fullname,
                    )

                    CoreViewModel.showSnackbar(("Success:" + response.data.message))

                }


                state = state.copy(
                    profileIsLoading = false
                )


            }

            Log.v("username", CoreViewModel.user.toString())

            job = null
        }
    }

    private fun onBackClick(navigator: DestinationsNavigator) {
        navigator.popBackStack()
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.OnSave -> {
                onSave(event.navigator, event.context)
            }

            is EditProfileEvent.OnBackClick -> onBackClick(navigator = event.navigator)
            is EditProfileEvent.OnLogOut -> onLogout(navigator = event.navigator, event.context)
        }
    }


}