package com.example.engaz.features.profile.view.viewmodels.edit_profile

import android.net.Uri
import com.example.engaz.core.viewmodel.CoreViewModel

data class EditProfileState(


    var profileImage : String = (CoreViewModel.user?.imageUrl?: "").toString(),
    var username : String = CoreViewModel.user?.username?: "",

    var pickedProfileImage : Uri? = null,


    var usernameTextField : String = CoreViewModel.user?.username?: "",
    var phoneTextField : String = (CoreViewModel.user?.phone?: "").toString(),

    var profileIsLoading : Boolean = false,
    var profileError : String? = null,
)
