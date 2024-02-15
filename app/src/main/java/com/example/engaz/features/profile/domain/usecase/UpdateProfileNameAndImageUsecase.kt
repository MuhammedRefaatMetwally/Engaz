package com.example.engaz.features.profile.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.engaz.core.util.Resource
import com.example.engaz.features.profile.data.entities.update_profile_name_and_image.UpdateProfileNameAndImageResponse
import com.example.engaz.features.profile.data.repo.ProfileRepoImpl
import javax.inject.Inject

class UpdateProfileNameAndImageUsecase @Inject constructor(private val repo: ProfileRepoImpl) {

    suspend operator fun invoke(
        token: String,
        fullName: String?,
        imageFile: Uri?,
        context : Context
    ): Resource<UpdateProfileNameAndImageResponse> {

        return repo.updateProfileNameAndImage(
            token, fullName, imageFile, context
        )

    }

}