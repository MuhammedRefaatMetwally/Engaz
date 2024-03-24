package com.example.engaz.features.home.view.viewmodels.main_info.complete_payment

data class CompletePaymentState(

    var isCVCSecure: Boolean = true,


    var cvc: String = "",
    val cvcError : String? = null
)
