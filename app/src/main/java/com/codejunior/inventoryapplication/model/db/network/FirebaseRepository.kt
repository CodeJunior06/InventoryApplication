package com.codejunior.inventoryapplication.model.db.network

import android.net.Uri
import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.UserFirebase
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.*
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage:FirebaseStorage
) : IFirebaseRepository {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult> {
        return withContext(dispatcher) {
            firebaseAuth.signInWithEmailAndPassword(
                userFirebase.email.trim(),
                userFirebase.pass.trim()
            )
        }
    }

    override fun getSession(): FirebaseUser? = firebaseAuth.currentUser

    override fun signOut() = firebaseAuth.signOut()

    override suspend fun insertProduct(product: Product): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_PRODUCT).document().set(product)
        }
    }

    override suspend fun insertProvider(provider: Provider): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_PROVIDER).document().set(provider)
        }
    }

    override suspend fun getAllProviderByUser(): Task<QuerySnapshot> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_PROVIDER)
                .whereEqualTo(NameFirebase.FIELD_PROVIDER_USER_ID, getSession()!!.uid).get()
        }
    }

    override suspend fun getAllUserTable(id: String): Task<QuerySnapshot> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_USER)
                .whereEqualTo(NameFirebase.FIELD_USER_ID, id).get()
        }
    }

    override suspend fun insertCategory(category: Category): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_CATEGORY).document().set(category)
        }
    }

    override suspend fun updateUserTable(): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_USER).document(getSession()!!.uid)
                .update(NameFirebase.FIELD_USER_IS_NEW, false)
        }
    }

    override suspend fun getCategoryByProvider(item: String): Task<QuerySnapshot> {
        return withContext(dispatcher) {
            firebaseFirestore.collection(NameFirebase.TABLE_CATEGORY)
                .whereEqualTo(NameFirebase.FIELD_CATEGORY_PROVIDER_ID, item)
                .whereEqualTo(NameFirebase.FIELD_CATEGORY_USER_ID, getSession()!!.uid).get()
        }
    }

    override suspend fun registerUser(email: String, pass: String): AuthResult {
        return withContext(dispatcher) {
            firebaseAuth.createUserWithEmailAndPassword(email.trim(), pass.trim()).await()
        }
    }

    override suspend fun registerUserTableFirestore() {
        firebaseFirestore
            .collection(NameFirebase.TABLE_USER).document(userApplication.id).set(
                User(
                    userApplication.id, userApplication.isNew
                )
            )
    }

    override suspend fun registerProcessKardex(kardex:Kardex) {
        return withContext(dispatcher){
            firebaseFirestore.collection(NameFirebase.TABLE_KARDEX).document().set(kardex)
        }
    }

    override suspend fun getKardexByDay(date:String): Task<QuerySnapshot> {
        return withContext(dispatcher){
            firebaseFirestore.collection(NameFirebase.TABLE_KARDEX).whereEqualTo(NameFirebase.FIELD_KARDEX_DATE,date).get()
        }
    }

    override suspend fun insertImage(uri: Uri, idProduct:String): UploadTask.TaskSnapshot {
        return withContext(dispatcher){
            firebaseStorage.getReference(getSession()!!.uid).child("product").child(idProduct).putFile(uri).result
        }
    }

}