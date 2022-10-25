package com.codejunior.inventoryapplication.model.db.network

import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.UserFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Category
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.QuerySnapshot


interface IFirebaseRepository {

    //SUSPEND
    suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult>

    suspend fun insertProduct(product: Product): Task<Void>

    suspend fun insertProvider(provider: Provider): Task<Void>

    suspend fun getAllProviderFB(): Task<QuerySnapshot>

    suspend fun getAllUserTable(): Task<QuerySnapshot>

    suspend fun insertCategory(category: Category): Task<Void>

    suspend fun updateUserTable() :Task<Void>

    //NO SUSPEND
    fun getSession(): FirebaseUser?

    fun signOut(success: () -> Unit)

}