package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import android.util.Log
import com.example.engaz.core.util.Resource
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.viewmodel.UserManager
import com.example.engaz.core.viewmodel.UserPreferences
import com.example.engaz.features.auth.data.entities.cars.CarsResponse
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import javax.inject.Inject

class ConfirmTransactionUseCase@Inject constructor(
    val repo: AuthRepoImpl
    ) {

    suspend operator fun invoke(
        address: String,
        context: Context
    ): Resource<CarsResponse> {

        val result = repo.confirmTransaction(
            address = address,
            context = context,
        )

        if(result.failure == null) {


            return result
        }

        return result

    }

}