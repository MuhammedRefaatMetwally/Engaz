package com.example.engaz.features.auth.infrastructure.api

import com.example.engaz.features.auth.data.entities.check_code_sent.CheckCodeSentResponse
import com.example.engaz.features.auth.data.entities.login.LoginResponse
import com.example.engaz.features.auth.data.entities.logout.LogoutResponse
import com.example.engaz.features.auth.data.entities.register.RegisterResponse
import com.example.engaz.features.auth.data.entities.resend_activition_code.ResendActivationCodeResponse
import com.example.engaz.features.auth.data.entities.reset_password.ResetPasswordResponse
import com.example.engaz.features.auth.data.entities.send_code_to_phone.SendCodeToPhoneResponse
import com.example.engaz.features.auth.infrastructure.api.request.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @Headers("Content-Type: application/json")
    @POST("api/auth/login")
    suspend fun login(
        @Body registerRequest: LoginRequest
    ): Response<LoginResponse>


    @Headers("Content-Type: application/json")
    @POST("api/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>


    @Headers("Content-Type: application/json")
    @POST("api/auth/confirm-code")
    suspend fun confirmCode(
        @Body confirmCodeRequest: ConfirmCodeRequest
    ): Response<RegisterResponse>

    @Headers("Content-Type: application/json")
    @POST("api/auth/send-code")
    suspend fun resendActivationCode(
        @Body resendActivationCodeRequest: ResendActivationCodeRequest
    ): Response<ResendActivationCodeResponse>

    @Headers("Content-Type: application/json")
    @POST("api/auth/logout")
    suspend fun sendSmsCode(
        @Body sendCodeToPhoneRequest: SendCodeToPhoneRequest
    ): Response<SendCodeToPhoneResponse>

    @Headers("Content-Type: application/json")
    @POST("api/check-code")
    suspend fun checkCodeSent(
        @Body checkCodeSentRequest: CheckCodeSentRequest
    ): Response<CheckCodeSentResponse>

    @Headers("Content-Type: application/json")
    @POST("api/reset-password")
    suspend fun resetPassword(
        @Body resetPasswordRequest: ResetPasswordRequest,
    ): Response<ResetPasswordResponse>


    @Headers("Content-Type: application/json")
    @POST("api/auth/logout")
    suspend fun logOut() : Response<LogoutResponse>

}