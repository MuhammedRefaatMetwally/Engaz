package com.example.engaz.features.transactions.domain.usecases.places

import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.infrastructure.services.PlacesService
import com.example.engaz.features.transactions.infrastructure.services.utils.PlaceInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchPlacesUseCase @Inject constructor(
    val service: PlacesService
) {

    suspend operator fun invoke(
        query: String,
    ): Flow<Resource<List<PlaceInfo>>> {

        return service.searchPlaces(query)

    }

}