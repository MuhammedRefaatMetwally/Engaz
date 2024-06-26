package com.example.engaz.core.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

object ViewModelProviderSingleton {
    private lateinit var viewModelProvider: ViewModelProvider

    fun getInstance(application: Application): ViewModelProvider {
        if (!::viewModelProvider.isInitialized) {
            viewModelProvider = ViewModelProvider(
                ViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            )
        }
        return viewModelProvider
    }
}