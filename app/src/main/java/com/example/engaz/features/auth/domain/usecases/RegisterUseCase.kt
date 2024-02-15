package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.register.RegisterResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.RegisterRequest
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    suspend operator fun invoke(
        registerRequest: RegisterRequest,
        context: Context
    ): Resource<RegisterResponse> {

        return repo.register(
            registerRequest,
            context
        )

    }

}