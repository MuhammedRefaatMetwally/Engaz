package com.example.engaz.features.wallet.domain.repo

import android.content.Context
import android.net.Uri
import com.example.engaz.core.util.Resource
import com.example.engaz.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.engaz.features.wallet.data.entities.get_balance.GetBalanceResponse

interface WalletRepo {

    suspend fun getBalance(
        token : String,
        context: Context
    ): Resource<GetBalanceResponse>

    suspend fun chargeBalance(
        token : String,
        image: Uri,
        context: Context
    ): Resource<ChargeBalanceResponse>

}