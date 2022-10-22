package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.db.model.Producto
import com.codejunior.inventoryapplication.model.UserFirebase
import com.codejunior.inventoryapplication.model.db.model.Provider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.QuerySnapshot


interface FirebaseRepository {
     suspend fun isSetAuthentication(userFirebase: UserFirebase) : Task<AuthResult>
     fun getSession():FirebaseUser?
     fun signOut(success:() -> Unit )
     suspend fun insertProduct(producto: Producto) : Task<Void>
     suspend fun insertProvider(provider:Provider) : Task<Void>
     suspend fun getAllProviderFB() : Task<QuerySnapshot>
}