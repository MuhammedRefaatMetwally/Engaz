package com.example.engaz.features.transactions.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.engaz.R
import com.example.engaz.features.auth.view.viewmodels.login.LoginViewModel
import com.example.engaz.features.profile.view.components.CarDetailsCard
import com.example.engaz.features.profile.view.components.Header
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.raamcosta.compose_destinations.destinations.SendTransferingRequestScreenDestination
import io.github.raamcosta.compose_destinations.destinations.TransferCarOwnershipScreenDestination
import kotlinx.coroutines.delay

@Destination()
@Composable
fun CarsScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: DestinationsNavigator?,
){
    val context = LocalContext.current

    var loading by remember { mutableStateOf(true) }

    // Simulate loading delay
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay for loading simulation
        loading = false
    }
    if (loading) {
        // Show loading indicator
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = colorResource(id = R.color.primary_color))
        }
    } else {
        // Show the actual content
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(Modifier.fillMaxWidth()) {
                    Header(label = stringResource(R.string.avaliable_cars)) {
                        navigator?.popBackStack()
                    }
                }
            }
            items(loginViewModel.carDetailsList) { item ->
                CarDetailsCard(carDetails = item) {
                    navigator?.navigate(SendTransferingRequestScreenDestination)
                }
            }
        }
    }
}