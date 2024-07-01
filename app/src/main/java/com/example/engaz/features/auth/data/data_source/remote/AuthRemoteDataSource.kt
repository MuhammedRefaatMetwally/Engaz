package com.example.engaz.features.auth.data.data_source.remote

import com.example.engaz.R
import com.example.engaz.core.errors.RemoteDataException
import com.example.engaz.features.auth.data.entities.cars.CarsResponse
import com.example.engaz.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.logout.LogoutResponse
import com.example.engaz.features.auth.data.entities.register.RegisterResponse
import com.example.engaz.features.auth.data.entities.resend_activition_code.ResendActivationCodeResponse
import com.example.engaz.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.engaz.features.auth.data.entities.sendTransaction.SendTransactionRequest
import com.example.engaz.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.engaz.features.auth.data.entities.transferOwnerShip.TransferOwnerShipRequest
import com.example.engaz.features.auth.infrastructure.api.AuthApi
import com.example.engaz.features.auth.infrastructure.api.request.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse>

    suspend fun register(
        registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest
    ): Response<RegisterResponse>

    suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivationCodeRequest
    ): Response<ResendActivationCodeResponse>

    suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse>

    suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse>

    suspend fun logout(): Response<LogoutResponse>

    suspend fun getCars(
         address:String
    ): Response<CarsResponse>

    suspend fun transferOwnerShip(transferOwnerShipRequest: TransferOwnerShipRequest): Response<CarsResponse>

    suspend fun sendTransaction(sendTransactionRequest: SendTransactionRequest): Response<CarsResponse>

    suspend fun getTransaction( address:String): Response<CarsResponse>

    suspend fun confirmTransaction( address:String): Response<CarsResponse>


    suspend fun searchAboutCar( number : String): Response<CarsResponse>
}

class AuthRemoteDataSourceImpl @Inject constructor(val api: AuthApi) : AuthRemoteDataSource {

    companion object {
        val DATA_SOURCE_ID = 0
    }

    override suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse> {

        try {
            return api.login(
                loginRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }

    }

    override suspend fun register(
        registerRequest: RegisterRequest
    ): Response<RegisterResponse> {

        try {

            return api.register(
                registerRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

    override suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest
    ): Response<RegisterResponse> {
        try {
            return api.confirmCode(
                confirmCodeRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }

    override suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivationCodeRequest
    ): Response<ResendActivationCodeResponse> {
        try {
            return api.resendActivationCode(
                resendActivitionCodeRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }


    override suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse> {
        try {
            return api.sendSmsCode(
                sendCodeToPhoneRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse> {
        try {

            return api.checkCodeSent(
                checkCodeSentRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest
    ): Response<ResetPasswordResponse> {
        try {

            return api.resetPassword(
                resetPasswordRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())

        }
    }

    override suspend fun logout(): Response<LogoutResponse> {
        try {

            return api.logOut()

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun getCars(address: String): Response<CarsResponse> {
         try {
            return api.getCars(
                address
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun transferOwnerShip(transferOwnerShipRequest: TransferOwnerShipRequest): Response<CarsResponse> {
         try {
            return api.transferOwnerShip(
                transferOwnerShipRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun sendTransaction(sendTransactionRequest: SendTransactionRequest): Response<CarsResponse> {
         try {
            return api.sendTransaction(
                sendTransactionRequest
            )

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun getTransaction(address: String): Response<CarsResponse> {
         try {
            return api.getTransaction(address)

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun confirmTransaction(address: String): Response<CarsResponse> {
         try {
            return api.confirmTransaction(address)

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

    override suspend fun searchAboutCar(number: String): Response<CarsResponse> {
         try {
            return api.searchAboutCar(number)

        } catch (e: Exception) {
            throw RemoteDataException(R.string.internet_connection.toString())
        }
    }

}