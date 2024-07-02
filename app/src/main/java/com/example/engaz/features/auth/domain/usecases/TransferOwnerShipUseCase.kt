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
import com.example.engaz.features.auth.data.entities.sendTransaction.SendTransactionRequest
import com.example.engaz.features.auth.data.entities.transferOwnerShip.TransferOwnerShipRequest
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import com.example.engaz.features.auth.infrastructure.api.request.LoginRequest
import javax.inject.Inject

class TransferOwnerShipUseCase@Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) {

    suspend operator fun invoke(
        transferOwnerShipRequest: TransferOwnerShipRequest,
        context: Context
    ): Resource<CarsResponse> {

            val result = repo.transferOwnerShip(
                transferOwnerShipRequest = transferOwnerShipRequest,
                context = context,
            )

            if (result.failure == null) {
                return result
            }

            return result

        }


}