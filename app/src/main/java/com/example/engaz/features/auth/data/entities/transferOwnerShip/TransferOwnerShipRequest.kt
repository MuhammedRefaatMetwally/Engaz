package com.example.engaz.features.auth.data.entities.transferOwnerShip

data class TransferOwnerShipRequest(
    val address :String ,
    val carId  : String,
    val toAddress:String
 )