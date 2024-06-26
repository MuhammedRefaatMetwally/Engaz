package com.example.engaz

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ScaffoldDefaults
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engaz.core.ui.theme.MarketAppTheme
import com.example.engaz.core.viewmodel.CoreViewModel
import com.example.engaz.core.viewmodel.ViewModelProviderSingleton
import com.example.engaz.core.views.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.FragmentComponent

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private lateinit var coreViewModel: CoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        coreViewModel = ViewModelProvider(this)[CoreViewModel::class.java]

        setContent {
            ScaffoldDefaults.contentWindowInsets // Fixes blank screen for Xiaomi devices

            MarketAppTheme {
                Navigation()
            }


        }
    }


}
