package com.example.engaz

import android.app.Application
import com.example.engaz.core.util.Consts
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(applicationContext, Consts.STRIPE_PUBLISH_KEY)
    }
}
