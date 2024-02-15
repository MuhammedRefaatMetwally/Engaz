package com.example.engaz

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ScaffoldDefaults
import com.example.engaz.core.ui.theme.MarketAppTheme
import com.example.engaz.core.views.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        setContent {
            ScaffoldDefaults.contentWindowInsets // Fixes blank screen for Xiaomi devices

            MarketAppTheme {
                Navigation()
            }


        }
    }


}
