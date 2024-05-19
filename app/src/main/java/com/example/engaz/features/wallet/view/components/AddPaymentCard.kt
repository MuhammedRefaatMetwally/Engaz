package com.example.engaz.features.wallet.view.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engaz.R
import com.example.engaz.core.ui.theme.Cairo
import com.example.engaz.core.views.components.MainButton

@ExperimentalAnimationApi
@Composable
fun AddPaymentCard(showDialog: MutableState<Boolean>) {
    var nameText by remember { mutableStateOf(TextFieldValue()) }
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expiryNumber by remember { mutableStateOf(TextFieldValue()) }
    var cvcNumber by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize()) {
        PaymentCard(
            nameText,
            cardNumber,
            expiryNumber,
            cvcNumber
        )
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            item {
                InputItem(
                    textFieldValue = nameText,
                    label = stringResource(R.string.name_card_ar),
                    onTextChanged = { nameText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }

            item {
                InputItem(
                    textFieldValue = cardNumber,
                    label = stringResource(R.string.card_number_ar),
                    keyboardType = KeyboardType.Number,
                    onTextChanged = { cardNumber = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    visualTransformation = CreditCardFilter
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputItem(
                        textFieldValue = expiryNumber,
                        label = stringResource(R.string.expiry_date_ar),
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { expiryNumber = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    InputItem(
                        textFieldValue = cvcNumber,
                        label = stringResource(id = R.string.cvc),
                        keyboardType = KeyboardType.Number,
                        onTextChanged = { cvcNumber = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                MainButton(
                    Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {
                            showDialog.value = true
                        },
                    cardColor = colorResource(id = R.color.primary_color)
                ) {
                    Text(
                        text = "إتمام الدفع",
                        fontFamily = Cairo,
                        fontWeight = FontWeight.W700,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                MainButton(
                    Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable {

                        },
                    cardColor = colorResource(id = R.color.white)
                ) {
                    Text(
                        text = "إلغاء",
                        fontFamily = Cairo,
                        fontWeight = FontWeight.W700,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}