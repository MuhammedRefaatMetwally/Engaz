package com.example.engaz.features.transactions.view.viewmodel.order

import android.net.Uri
import com.example.engaz.features.transactions.data.entities.orders.Order
import java.io.File

data class OrderState(

    var description : String = "",
    var isMakingOrderStep1 : Boolean = false,
    var makeOrdersError : String? = null,

    var isOrdersLoading : Boolean = false,
    var orders: List<Order> = emptyList(),
    var ordersError : String? = null,

    var images: List<Uri> = emptyList(),
    var records: List<File> = emptyList(),
    var files: List<Uri> = emptyList(),
)
