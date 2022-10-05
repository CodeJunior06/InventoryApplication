package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.firebase.auth.FirebaseUser


interface FirebaseRepository {
     suspend fun isSetAuthentication(userFirebase: UserFirebase) :FirebaseUser?
}