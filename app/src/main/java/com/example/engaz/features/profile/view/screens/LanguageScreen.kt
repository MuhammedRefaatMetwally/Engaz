package com.example.engaz.features.profile.view.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.engaz.R
import com.example.engaz.core.ui.theme.*
import com.example.engaz.features.profile.view.components.Header
import com.example.engaz.features.profile.view.components.LanguageItem
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun LanguageScreen(
    navigator: DestinationsNavigator?,
){
    val context: Context = LocalContext.current

    Scaffold(
        containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral100
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(30.dp))


            Header(
                label = context.getString(R.string.language),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(45.dp))

            LanguageItem(
                selected = true,
                label = context.getString(R.string.english),
                languageTag = "eg",
                onClick = {},
            )

            LanguageItem(
                selected = false,
                label = context.getString(R.string.arabic),
                languageTag = "ar",
                onClick = {},
            )



        }
    }



}



@Preview
@Composable
fun LanguageScreenPreview(){
    LanguageScreen(navigator = null)
}