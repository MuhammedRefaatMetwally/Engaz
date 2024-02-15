package com.example.engaz.features.wallet.domain.usecase

import android.content.Context
import android.net.Uri
import com.example.engaz.core.util.Resource
import com.example.engaz.features.wallet.data.entities.charge_balance.ChargeBalanceResponse
import com.example.engaz.features.wallet.data.repo.WalletRepoImpl
import javax.inject.Inject

class ChargeBalanceUseCase @Inject constructor(private val repo : WalletRepoImpl){

    suspend operator fun invoke(
        token: String,
        image: Uri,
        context: Context
    ): Resource<ChargeBalanceResponse> {

        return repo.chargeBalance(
            token, image, context
        )

    }

}