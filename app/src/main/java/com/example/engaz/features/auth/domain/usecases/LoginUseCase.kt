package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) {

    suspend operator fun invoke(
        loginRequest: LoginRequest,
        context: Context
    ): Resource<LoginResponse> {

        val result = repo.login(
            loginRequest = loginRequest,
            context = context,
        )

        if(result.failure == null) {

            val user = UserLogin(
                result.data!!.data?.user?.phone,
                result.data.data?.user?.isAdmin,
                result.data.data?.user?.imageUrl,
                result.data.data?.user?.otpCode,
                result.data.data?.user?.id,
                result.data.data?.user?.token,
                result.data.data?.user?.email,
                result.data.data?.user?.isVerified.toString()
                )

            val saveResult = saveUserInfoUseCase(user,context,0)

            CoreViewModel.user = user


            return if(saveResult.failure == null) {
                result
            } else  {
                Resource.FailureData(saveResult.failure)
            }
        }

        return result

    }

}