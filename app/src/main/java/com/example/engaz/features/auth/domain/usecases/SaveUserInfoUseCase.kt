package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    operator fun invoke(
        user: UserLogin,
        context: Context,
        screenId: Int
    ): Resource.FailureData<UserLogin> {

        return repo.saveUserInfo(
            context, user, screenId
        )

    }
}