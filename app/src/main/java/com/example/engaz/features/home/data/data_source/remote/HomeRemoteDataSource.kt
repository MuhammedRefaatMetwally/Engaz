package com.example.engaz.features.home.data.data_source.remote

import com.example.engaz.core.errors.RemoteDataException
import com.example.engaz.features.home.data.entities.home.HomeResponse
import com.example.engaz.features.home.infrastructure.api.HomeApi
import retrofit2.Response
import javax.inject.Inject

interface HomeRemoteDataSource {

    suspend fun home(token : String) : Response<HomeResponse>

}


class HomeRemoteDataSourceImpl @Inject constructor(val api: HomeApi) : HomeRemoteDataSource {

    override suspend fun home(token: String): Response<HomeResponse> {
        try {
            return api.home(
                token = token
            )

        } catch (e: Exception) {
            throw RemoteDataException(e.message.toString())

        }
    }

}