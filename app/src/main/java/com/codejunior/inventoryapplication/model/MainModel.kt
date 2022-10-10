package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirestoreImp
import com.codejunior.inventoryapplication.network.FirebaseController
import javax.inject.Inject

class MainModel @Inject constructor(private val firestoreImp: FirestoreImp) {

    fun signOut(success: () -> Unit){
        firestoreImp.signOut(success)
    }
}