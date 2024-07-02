package com.example.engaz.features.auth.domain.repo

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.auth.data.entities.cars.CarsResponse
import com.example.engaz.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.login.User
import com.example.engaz.features.auth.data.entities.login.UserLogin
import com.example.engaz.features.auth.data.entities.logout.LogoutResponse
import com.example.engaz.features.auth.data.entities.register.RegisterResponse
import com.example.engaz.features.auth.data.entities.resend_activition_code.ResendActivationCodeResponse
import com.example.engaz.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.engaz.features.auth.data.entities.sendTransaction.SendTransactionRequest
import com.example.engaz.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.engaz.features.auth.data.entities.transferOwnerShip.TransferOwnerShipRequest
import com.example.engaz.features.auth.infrastructure.api.request.*
import retrofit2.Response

interface AuthRepo {
    suspend fun login(
        loginRequest: LoginRequest,
        context: Context,
    ): Resource<LoginResponse>

    suspend fun register(
        registerRequest: RegisterRequest,
        context: Context
    ): Resource<RegisterResponse>

    suspend fun confirmCode(
        confirmCodeRequest: ConfirmCodeRequest,
        context: Context
    ): Resource<RegisterResponse>

    suspend fun resendActivitionCode(
        resendActivitionCodeRequest: ResendActivationCodeRequest,
        context: Context
    ): Resource<ResendActivationCodeResponse>

    suspend fun sendCodeToPhone(
        sendCodeToPhoneRequest: SendCodeToPhoneRequest,
        context: Context,
    ): Resource<SendCodeToPhoneResponse>

    suspend fun checkCodeSent(
        checkCodeSentRequest: CheckCodeSentRequest,
        context: Context,
    ): Resource<CheckCodeSentResponse>

    suspend fun resetPassword(
        resetPasswordRequest: ResetPasswordRequest,
        context: Context,
    ): Resource<ResetPasswordResponse>

    suspend fun logout(context: Context): Resource<LogoutResponse>

    fun getUserInfo(
        context: Context,
        screenId: Int

    ): Resource<UserLogin>

    fun saveUserInfo(
        context: Context,
        user: UserLogin,
        screenId: Int
    ): Resource.FailureData<UserLogin>

    fun deleteUserInfo(
        context: Context,
        screenId: Int
    ): Resource.FailureData<UserLogin>?

    suspend fun getCars(
        address:String,
        context: Context
    ): Resource<CarsResponse>

    suspend fun transferOwnerShip(transferOwnerShipRequest: TransferOwnerShipRequest,context: Context,): Resource<CarsResponse>

    suspend fun sendTransaction(sendTransactionRequest: SendTransactionRequest,context: Context): Resource<CarsResponse>

    suspend fun getTransaction( address:String,context: Context): Resource<CarsResponse>

    suspend fun confirmTransaction( address:String,context: Context): Resource<CarsResponse>


    suspend fun searchAboutCar( number : String,context: Context): Resource<CarsResponse>
}