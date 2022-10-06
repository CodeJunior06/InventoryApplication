package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.network.FirebaseController

class MainModel {
    private val firebaseController = FirebaseController()
    fun signOut(success: () -> Unit){
        firebaseController.signOut(success)
    }
}