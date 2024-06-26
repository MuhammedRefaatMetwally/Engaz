package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.register.RegisterResponse

import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.ConfirmCodeRequest
import javax.inject.Inject


class ConfirmCodeUseCase @Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,
) {

    suspend operator fun invoke(
        confirmCodeRequest: ConfirmCodeRequest,
        context: Context
    ): Resource<RegisterResponse> {

        val result = repo.confirmCode(
            confirmCodeRequest,
            context
        )


        return result


    }




}