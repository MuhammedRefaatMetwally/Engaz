package com.example.engaz.features.transactions.domain.usecases.order

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.orders.OrdersResponse
import com.example.engaz.features.transactions.data.repo.OrderRepoImpl
import javax.inject.Inject


class OrderUseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<OrdersResponse> {

        return repo.orders(token, context)

    }

}