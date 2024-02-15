package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.ResetPasswordRequest
import javax.inject.Inject


class ResetPasswordUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        resetPasswordRequest: ResetPasswordRequest,
        context: Context,
    ): Resource<ResetPasswordResponse> {

        return repo.resetPassword(resetPasswordRequest, context)

    }

}