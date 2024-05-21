package com.example.engaz.core.util.usecase

import android.content.Context
import com.example.engaz.R
import com.example.engaz.core.errors.Failure
import com.example.engaz.core.util.Resource
import com.example.engaz.core.util.validator.Validator
import javax.inject.Inject

class ValidatePhoneLocalUseCase @Inject constructor(
    val validator : Validator
) {

    operator fun invoke(usernameOrPassCode: String, context: Context): Resource<Boolean?> {

        if (usernameOrPassCode.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = "أدخل اسم المستخدم أو الرقم القومي",
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }
        val regex = Regex("[0123456789]")
        if (!validator.isValidPassCode(usernameOrPassCode) && usernameOrPassCode.contains(regex)) {
            return Resource.FailureData(
                Failure(
                    message ="يجب أن يكون الرقم القومي مكون من 14",
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