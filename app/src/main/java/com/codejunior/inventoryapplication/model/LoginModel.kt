package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirestoreImp
import com.codejunior.inventoryapplication.network.FirebaseController
import javax.inject.Inject

class LoginModel (val email: String, val password: String) {
    private val firebaseController = FirebaseController()

    fun auth(success: () -> Unit, error: () -> Unit) {
        firebaseController.auth(this, success, error)
    }

}