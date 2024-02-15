package com.example.engaz.features.transactions.infrastructure.api.requests

import com.google.gson.annotations.SerializedName

data class MakeOrderStep2Request(
    @SerializedName("from_lat") val fromLat: Double,
    @SerializedName("from_lng") val fromLng: Double,
    @SerializedName("to_lat") val toLat: Double,
    @SerializedName("to_lng") val toLng: Double,
    @SerializedName("order_id") val orderId: Int,
)
