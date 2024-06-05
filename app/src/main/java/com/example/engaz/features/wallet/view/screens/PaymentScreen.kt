/*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.core.util.Consts
import com.example.engaz.features.wallet.view.viewmodel.wallet.WalletViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Destination
@Composable
fun PaymentScreen(paymentViewModel: WalletViewModel = hiltViewModel()) {

    val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        // Fetch payment intent and customer configuration
        paymentViewModel.makePayment { clientSecret, customerId, ephemeralKey ->
            paymentIntentClientSecret = clientSecret
            customerConfig = PaymentSheet.CustomerConfiguration(customerId ?: "", ephemeralKey ?:"")
        }
    }

    Button(
        onClick = {
            if (paymentIntentClientSecret != null && customerConfig != null) {
                presentPaymentSheet(paymentSheet, paymentIntentClientSecret!!, customerConfig!!)
            }
        }
    ) {
        Text("Checkout")
    }
}

fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    paymentIntentClientSecret: String,
    customerConfig: PaymentSheet.CustomerConfiguration
) {
    paymentSheet.presentWithPaymentIntent(
        paymentIntentClientSecret,
        PaymentSheet.Configuration(
            merchantDisplayName = "crow",
            customer = customerConfig
        )
    )
}

fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            println("Canceled")
        }

        is PaymentSheetResult.Failed -> {
            println("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            println("Completed")
        }
    }
}
*/
