package com.codejunior.inventoryapplication.model.db

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class FirestoreImp @Inject constructor(val authFirebase: FirebaseAuth) : FirebaseRepository {
    override suspend fun getAuthentication() {

    }

}

