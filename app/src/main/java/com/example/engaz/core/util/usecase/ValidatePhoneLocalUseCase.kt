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

    operator fun invoke(email: String, context: Context): Resource<Boolean?> {

        if (email.isBlank()) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.string_is_blank),
                    screenIdInt = 0,
                    exceptionCode = 0,
                    customCode = 0
                )
            )
        }

        if (!validator.isValidPhone(email)) {
            return Resource.FailureData(
                Failure(
                    message = context.getString(R.string.string_is_not_phone),
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