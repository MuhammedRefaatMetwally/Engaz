package com.example.engaz.features.transactions.domain.repo

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.cancel_order.CancelOrderResponse
import com.example.engaz.features.transactions.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.engaz.features.transactions.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.engaz.features.transactions.data.entities.order_details.OrderDetailsResponse
import com.example.engaz.features.transactions.data.entities.orders.OrdersResponse
import com.example.engaz.features.transactions.infrastructure.api.requests.*

interface OrderRepo {

    suspend fun makeOrderStep1(
        token : String,
        makeOrderStep1Input: MakeOrderStep1Input,
        context: Context
    ): Resource<MakeOrderStep1Response>


    suspend fun makeOrderStep2(
        token : String,
        makeOrderStep2Request: MakeOrderStep2Request,
        context: Context
    ): Resource<MakeOrderStep2Response>


    suspend fun cancelOrder(
        token : String,
        cancelOrderRequest: CancelOrderRequest,
        context: Context
    ): Resource<CancelOrderResponse>


    suspend fun orderDetails(
        token : String,
        orderDetailsRequest: OrderDetailsRequest,
        context: Context
    ): Resource<OrderDetailsResponse>


    suspend fun orders(
        token : String,
        context: Context
    ): Resource<OrdersResponse>

}