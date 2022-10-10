package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser


interface FirebaseRepository {
     suspend fun isSetAuthentication(userFirebase: UserFirebase) : Task<AuthResult>
     fun getSession():FirebaseUser?
     fun signOut(success:() -> Unit )
}