package com.example.engaz.features.home.infrastructure.api

import com.example.engaz.features.auth.infrastructure.api.request.*
import com.example.engaz.features.home.data.entities.home.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HomeApi {

    @Headers("Content-Type: application/json")
    @GET("api/home")
    suspend fun home(
        @Header("Authorization") token : String
    ): Response<HomeResponse>

}