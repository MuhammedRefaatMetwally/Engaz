package com.example.engaz.features.wallet.infrastructure.api

import com.example.engaz.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.engaz.features.wallet.data.entities.get_balance.GetBalanceResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface WalletApi {

    @Headers("Content-Type: application/json")
    @GET("api/get-balance")
    suspend fun getBalance(
        @Header("Authorization") token : String,
    ): Response<GetBalanceResponse>

    @Multipart
    @POST("api/charge-balance")
    suspend fun chargeBalance(
        @Header("Authorization") token : String,
        @Part image : MultipartBody.Part
    ): Response<ChargeBalanceResponse>

}

