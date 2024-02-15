package com.example.engaz.features.transactions.infrastructure.api.requests

import com.google.gson.annotations.SerializedName

data class CancelOrderRequest(
    @SerializedName("order_id") val orderId: Int,
)
