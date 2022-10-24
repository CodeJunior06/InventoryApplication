package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.db.model.Product
import com.codejunior.inventoryapplication.model.UserFirebase
import com.codejunior.inventoryapplication.model.db.model.Provider
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) :
    IFirebaseRepository {

    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    override suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult> {
        return withContext(dispatcher) {
            firebaseAuth.signInWithEmailAndPassword(
                userFirebase.email.trim(),
                userFirebase.pass.trim()
            )
        }
    }

    override fun getSession(): FirebaseUser? = firebaseAuth.currentUser
    override fun signOut(success: () -> Unit) {
        firebaseAuth.signOut()
        success.invoke()
    }

    override suspend fun insertProduct(product: Product): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection("product").document().set(product)
        }
    }

    override suspend fun insertProvider(provider: Provider): Task<Void> {
        return withContext(dispatcher) {
            firebaseFirestore.collection("provider").document().set(provider)
        }
    }

    override suspend fun getAllProviderFB(): Task<QuerySnapshot> {
        return withContext(dispatcher) {
            firebaseFirestore.collection("provider").get()
        }
    }

}