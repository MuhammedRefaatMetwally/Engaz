package com.example.engaz.features.transactions.domain.usecases.order

import android.content.Context
import com.example.engaz.core.util.Resource
import com.example.engaz.features.transactions.data.entities.make_order_step_2.MakeOrderStep2Response
import com.example.engaz.features.transactions.data.repo.OrderRepoImpl
import com.example.engaz.features.transactions.infrastructure.api.requests.MakeOrderStep2Request
import javax.inject.Inject


class MakeOrderStep2UseCase @Inject constructor(
    val repo: OrderRepoImpl
) {

    suspend operator fun invoke(
        token: String,
        makeOrderStep2Request: MakeOrderStep2Request,
        context: Context
    ): Resource<MakeOrderStep2Response> {

        return repo.makeOrderStep2(token, makeOrderStep2Request, context)

    }

}