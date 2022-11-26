package com.codejunior.inventoryapplication.model.db.network

import android.net.Uri
import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.UserFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Category
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask


interface IFirebaseRepository {

    //SUSPEND
    suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult>

    suspend fun insertProduct(product: Product): Task<Void>

    suspend fun insertProvider(provider: Provider): Task<Void>

    suspend fun getAllProviderByUser(): Task<QuerySnapshot>

    suspend fun getAllUserTable(id:String): Task<QuerySnapshot>

    suspend fun insertCategory(category: Category): Task<Void>

    suspend fun updateUserTable() :Task<Void>

    suspend fun getCategoryByProvider(item:String) : Task<QuerySnapshot>

    suspend fun registerUser(email:String,pass:String) : AuthResult

    suspend fun registerUserTableFirestore()

    suspend fun registerProcessKardex(kardex: Kardex)

    suspend fun getKardexByDay(date:String) :  Task<QuerySnapshot>

    suspend fun insertImage(uri:Uri,idProduct:String): UploadTask

    suspend fun getAllProduct() : Task<QuerySnapshot>

    //NO SUSPEND
    fun getSession(): FirebaseUser?

    fun signOut()

}