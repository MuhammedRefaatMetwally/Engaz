package com.example.engaz.features.profile.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.profile.data.entities.update_phone_number_step_2.UpdatePhoneNumberStep2Response
import com.example.engaz.features.profile.data.repo.ProfileRepoImpl
import com.example.engaz.features.profile.infrastructure.api.request.UpdatePhoneNumberStep2Request
import javax.inject.Inject

class UpdatePhoneNumberStep2Usecase @Inject constructor(private val repo: ProfileRepoImpl) {

    suspend operator fun invoke(
        token: String,
        updatePhoneNumberStep2Request: UpdatePhoneNumberStep2Request,
        context: Context
    ): Resource<UpdatePhoneNumberStep2Response> {

        return repo.updatePhoneNumberStep2(
            token, updatePhoneNumberStep2Request, context
        )

    }

}