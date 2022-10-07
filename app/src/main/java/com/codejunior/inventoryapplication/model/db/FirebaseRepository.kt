package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


interface FirebaseRepository {
     suspend fun isSetAuthentication(userFirebase: UserFirebase) : Task<AuthResult>
}