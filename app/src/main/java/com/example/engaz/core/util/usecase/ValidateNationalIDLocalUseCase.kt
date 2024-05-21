package com.example.engaz.core.util.usecase

import android.content.Context
import com.example.engaz.R
import com.example.engaz.core.errors.Failure
import com.example.engaz.core.util.Resource
import javax.inject.Inject

class ValidateNationalIDLocalUseCase @Inject constructor()  {

    operator fun invoke(passCode: String, context: Context): Resource<Boolean?> {

        if (passCode.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = "أدخل الرقم القومي",
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        val regex = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/~]")
        if (passCode.contains(regex)) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.username_contain_special_char),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (passCode.length > 14 || passCode.length < 14) {
            return Resource.FailureData(
                Failure(
                    message = "يجب أن يكون الرقم القومي مكون من 14",
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