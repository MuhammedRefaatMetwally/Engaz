package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.resend_activition_code.ResendActivitionCodeResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.ResendActivitionCodeRequest
import javax.inject.Inject


class ResendActivitionCodeUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        confirmCodeRequest: ResendActivitionCodeRequest,
        context: Context
    ): Resource<ResendActivitionCodeResponse> {
        return repo.resendActivitionCode(
            confirmCodeRequest,
            context
        )
    }

}