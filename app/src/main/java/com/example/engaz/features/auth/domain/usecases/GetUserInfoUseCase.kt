package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    val repo: AuthRepoImpl
) {

    operator fun invoke(
        context: Context,
        screenId: Int
    ): Resource<User> {

        return repo.getUserInfo(
            context,screenId
        )

    }
}