package com.codejunior.inventoryapplication

import android.app.Application
import com.codejunior.inventoryapplication.model.db.network.model.UserApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InventoryApplication : Application(){

    companion object{
       var userApplication = UserApplication()
    }
}