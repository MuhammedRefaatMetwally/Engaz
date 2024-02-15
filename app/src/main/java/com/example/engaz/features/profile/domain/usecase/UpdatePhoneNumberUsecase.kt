package com.example.engaz.features.profile.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.profile.data.entities.update_phone_number.UpdatePhoneNumberResponse
import com.example.engaz.features.profile.data.repo.ProfileRepoImpl
import com.example.engaz.features.profile.infrastructure.api.request.UpdatePhoneNumberRequest
import javax.inject.Inject

class UpdatePhoneNumberUsecase @Inject constructor(private val repo: ProfileRepoImpl) {

    suspend operator fun invoke(
        token: String,
        updatePhoneNumberRequest: UpdatePhoneNumberRequest,
        context: Context
    ): Resource<UpdatePhoneNumberResponse> {

        return repo.updatePhoneNumber(
            token, updatePhoneNumberRequest, context
        )

    }

}