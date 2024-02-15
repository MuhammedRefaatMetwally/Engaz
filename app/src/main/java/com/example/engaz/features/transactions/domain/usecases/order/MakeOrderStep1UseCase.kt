package com.example.engaz.features.transactions.domain.usecases.order

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.make_order_step_1.MakeOrderStep1Response
import com.example.engaz.features.transactions.data.repo.OrderRepoImpl
import com.example.engaz.features.transactions.infrastructure.api.requests.MakeOrderStep1Input
import javax.inject.Inject


class MakeOrderStep1UseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        makeOrderStep1Input: MakeOrderStep1Input,
        context: Context
    ): Resource<MakeOrderStep1Response> {

        return repo.makeOrderStep1(token, makeOrderStep1Input, context)

    }

}