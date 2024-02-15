package com.example.engaz.features.transactions.data.entities.orders

data class OrdersResponse(
    val `data`: Data,
    val message: String,
    val result: String,
    val status: Int
)