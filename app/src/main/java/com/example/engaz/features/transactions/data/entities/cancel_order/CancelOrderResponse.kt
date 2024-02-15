package com.example.engaz.features.transactions.data.entities.cancel_order

data class CancelOrderResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)