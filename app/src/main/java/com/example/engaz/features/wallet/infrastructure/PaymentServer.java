package com.example.engaz.features.wallet.infrastructure;

import static spark.Spark.post;

import com.example.engaz.core.util.Consts;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.model.Customer;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.EphemeralKeyCreateParams;
import com.stripe.param.PaymentIntentCreateParams;

import java.util.HashMap;
import java.util.Map;

public class PaymentServer {
    public static void main(String[] args) {
        // Set your secret key. Remember to switch to your live secret key in production.
        // See your keys here: https://dashboard.stripe.com/apikeys
        Stripe.apiKey = Consts.STRIPE_SECRET_KEY;

        post("/payment-sheet", (request, response) -> {
            response.type("application/json");

            // Use an existing Customer ID if this is a returning customer.
            CustomerCreateParams customerParams = CustomerCreateParams.builder().build();
            Customer customer = Customer.create(customerParams);
            EphemeralKeyCreateParams ephemeralKeyParams = EphemeralKeyCreateParams.builder()
                    .setStripeVersion("2023-10-16")
                    .setCustomer(customer.getId())
                    .build();

            EphemeralKey ephemeralKey = EphemeralKey.create(ephemeralKeyParams);
            PaymentIntentCreateParams paymentIntentParams = PaymentIntentCreateParams.builder()
                    .setAmount(1099L)
                    .setCurrency("eur")
                    .setCustomer(customer.getId())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("paymentIntent", paymentIntent.getClientSecret());
            responseData.put("ephemeralKey", ephemeralKey.getSecret());
            responseData.put("customer", customer.getId());
            responseData.put("publishableKey", "pk_test_51Ow3KlRsJ1oPTnGIDW5Ztxw3bOwxpseH8mWzkuXEJtMuZPVsprR9Hl5VtxRPXCHLwWY1mRO5uPMm6RktFhRYaWha00RY1MIpe2");

            return new Gson().toJson(responseData);
        });
    }
}
