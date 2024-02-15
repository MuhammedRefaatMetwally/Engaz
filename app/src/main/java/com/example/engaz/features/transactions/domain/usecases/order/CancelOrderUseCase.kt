package com.example.engaz.features.transactions.domain.usecases.order

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.cancel_order.CancelOrderResponse
import com.example.engaz.features.transactions.data.repo.OrderRepoImpl
import com.example.engaz.features.transactions.infrastructure.api.requests.CancelOrderRequest
import javax.inject.Inject


class CancelOrderUseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        cancelOrderRequest: CancelOrderRequest,
        context: Context
    ): Resource<CancelOrderResponse> {

        return repo.cancelOrder(token, cancelOrderRequest, context)

    }

}