package com.example.engaz.features.wallet.infrastructure

import com.example.engaz.core.util.Consts
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreateCustomerResponse
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreateEpheralKeyResponse
import com.example.engaz.features.wallet.data.entities.stripe_payment.CreatePaymentIntentResponse
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

private const val SECRET = Consts.STRIPE_SECRET_KEY

interface StripeApiService {

    @Headers(
        "Authorization: Bearer $SECRET",
        "Stripe-Version: 2023-10-16"
    )
    @POST("v1/customers")
    suspend fun createCustomer() : CreateCustomerResponse

    @Headers(
        "Authorization: Bearer $SECRET",
        "Stripe-Version: 2023-10-16"
    )
    @POST("v1/ephemeral_keys")
    suspend fun createEphemeralKey(
        @Query("customer") customerId: String
    ): CreateEpheralKeyResponse

    @Headers(
        "Authorization: Bearer $SECRET"
    )
    @POST("v1/payment_intents")
    suspend fun createPaymentIntent(
        @Query("customer") customerId: String,
        @Query("amount") amount: Int,
        @Query("currency") currency: String,
        @Query("automatic_payment_methods[enabled]") autoPaymentMethodsEnable: Boolean,
    ): CreatePaymentIntentResponse

}
