package com.example.engaz.features.transactions.data.entities.order_details

data class OrderDetailsResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)