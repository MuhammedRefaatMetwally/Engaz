package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.resend_activition_code.ResendActivationCodeResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.ResendActivationCodeRequest
import javax.inject.Inject


class ResendActivitionCodeUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        confirmCodeRequest: ResendActivationCodeRequest,
        context: Context
    ): Resource<ResendActivationCodeResponse> {
        return repo.resendActivitionCode(
            confirmCodeRequest,
            context
        )
    }

}