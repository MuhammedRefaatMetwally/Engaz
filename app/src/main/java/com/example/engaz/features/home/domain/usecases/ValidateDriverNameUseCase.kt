package com.example.engaz.features.home.domain.usecases

import android.content.Context
import com.example.engaz.R
import com.example.engaz.core.errors.Failure
import com.example.engaz.core.util.Resource
import javax.inject.Inject

class ValidateDriverNameUseCase @Inject constructor()  {

    operator fun invoke(driverName: String, context: Context): Resource<Boolean?> {

        if (driverName.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = "ادخل اسم صاحب المركبة",
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        val regex = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~]")
        if (driverName.contains(regex)) {
            return Resource.FailureData(
                Failure(
                    message = "لا يجب أن يحتوي على هذه الرموز",
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        return Resource.SuccessData(
            true
        )


    }
}