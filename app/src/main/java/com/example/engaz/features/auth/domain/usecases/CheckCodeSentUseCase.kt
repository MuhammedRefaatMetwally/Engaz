package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.CheckCodeSentRequest
import javax.inject.Inject


class CheckCodeSentUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        checkCodeSentRequest: CheckCodeSentRequest,
        context: Context
    ): Resource<CheckCodeSentResponse> {

        return repo.checkCodeSent(checkCodeSentRequest, context)

    }

}