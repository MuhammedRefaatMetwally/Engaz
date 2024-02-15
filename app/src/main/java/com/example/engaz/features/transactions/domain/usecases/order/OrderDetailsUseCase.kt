package com.example.engaz.features.transactions.domain.usecases.order

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.order_details.OrderDetailsResponse
import com.example.engaz.features.transactions.data.repo.OrderRepoImpl
import com.example.engaz.features.transactions.infrastructure.api.requests.OrderDetailsRequest
import javax.inject.Inject


class OrderDetailsUseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        orderDetailsRequest: OrderDetailsRequest,
        context: Context
    ): Resource<OrderDetailsResponse> {

        return repo.orderDetails(token, orderDetailsRequest, context)

    }

}