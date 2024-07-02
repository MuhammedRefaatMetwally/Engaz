package com.example.engaz.features.auth.domain.usecases

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.cars.CarsResponse
import com.example.engaz.features.auth.data.repo.AuthRepoImpl
import javax.inject.Inject

class SearchAboutCarUseCase @Inject constructor(
    val repo: AuthRepoImpl,
    val saveUserInfoUseCase: SaveUserInfoUseCase,

    ) {

    suspend operator fun invoke(
        number: String,
        context: Context
    ): Resource<CarsResponse> {

        val result = repo.searchAboutCar(
            number = number,
            context = context,
        )

        if (result.failure == null) {
            return result
        }

        return result

    }
}