package com.codejunior.inventoryapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InventoryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}