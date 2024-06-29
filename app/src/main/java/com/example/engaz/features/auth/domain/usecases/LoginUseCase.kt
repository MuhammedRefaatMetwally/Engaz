package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import android.util.Log
import com.example.engaz.core.util.Resource
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import com.example.engaz.core.viewmodel.UserManager
import com.example.engaz.core.viewmodel.UserPreferences
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
              phone =   result.data!!.data?.user?.phone,
               isAdmin =  result.data.data?.user?.isAdmin,
               imageUrl =  result.data.data?.user?.imageUrl,
                otpCode = result.data.data?.user?.otpCode,
                id = result.data.data?.user?.id,
                username = result.data.data?.user?.username,
                token = result.data.data?.user?.token,
                email = result.data.data?.user?.email,
               isVerified =  result.data.data?.user?.isVerified.toString()
                )
            UserPreferences.saveUser(context, user)
            val saveResult = saveUserInfoUseCase(user,context,0)
            UserManager.user = user
            CoreViewModel.user = user
            Log.d("token3", "invoke: ${ UserManager.user?.token}")
            Log.d("token4", "invoke: ${ CoreViewModel.user?.token}")

            return if(saveResult.failure == null) {
                result
            } else  {
                Resource.FailureData(saveResult.failure)
            }
        }

        return result

    }

}