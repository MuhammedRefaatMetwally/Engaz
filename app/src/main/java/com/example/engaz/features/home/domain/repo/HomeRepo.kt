package com.example.engaz.features.home.domain.repo

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.home.data.entities.home.HomeResponse

interface HomeRepo {

    suspend fun home(token : String,context: Context): Resource<HomeResponse>

}