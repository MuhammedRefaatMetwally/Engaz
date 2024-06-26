package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.logout.LogoutResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import com.stripe.android.model.Token
import javax.inject.Inject

class LogoutUseCase @Inject constructor(val repo: AuthRepoImpl)  {

    suspend operator fun invoke(
        context: Context
    ): Resource<LogoutResponse> {
        return   repo.logout(
            context = context,
        )
    }
}