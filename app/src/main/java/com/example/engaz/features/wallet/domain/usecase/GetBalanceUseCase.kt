package com.example.engaz.features.wallet.domain.usecase

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.wallet.data.entities.get_balance.GetBalanceResponse
import com.example.engaz.features.wallet.data.repo.WalletRepoImpl
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(private val repo : WalletRepoImpl) {

    suspend operator fun invoke(
        token: String,
        context: Context
    ): Resource<GetBalanceResponse> {

        return repo.getBalance(
            token, context
        )

    }

}