package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.SendCodeToPhoneRequest
import javax.inject.Inject


class SendCodeToPhoneUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest,
        context: Context
    ): Resource<SendCodeToPhoneResponse> {

        return repo.sendCodeToPhone(sendCodeToPhoneRequest, context)

    }

}