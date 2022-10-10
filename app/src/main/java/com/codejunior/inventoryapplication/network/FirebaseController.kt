package com.codejunior.inventoryapplication.network

import com.codejunior.inventoryapplication.model.LoginModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseController {

    private var instance: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    fun hasSession(): Boolean {
        return instance.currentUser != null
    }

/*    fun auth(model: LoginModel, success: () -> Unit, error: () -> Unit) {
        instance.signInWithEmailAndPassword(model.email, model.password).addOnCompleteListener {
            if (it.isSuccessful) {
                success.invoke()
            } else {
                error.invoke()
            }
        }
    }*/

    fun signOut(success: () -> Unit) {
        instance.signOut()
        success.invoke()
    }
}