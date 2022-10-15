package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.Producto
import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreImp @Inject constructor(private val authFirebase: FirebaseAuth,private val firestore:FirebaseFirestore) :
    FirebaseRepository {

    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    override suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult> {
        return withContext(dispatcher) {
           authFirebase.signInWithEmailAndPassword(
                userFirebase.email.toString().trim(),
                userFirebase.pass.toString().trim()
            )
        }
    }

    override fun getSession(): FirebaseUser? =  authFirebase.currentUser
    override fun signOut(success: () -> Unit) {
        authFirebase.signOut()
        success.invoke()
    }

    override suspend fun insertProduct(producto: Producto): Task<Void> {
        return withContext(dispatcher) {
            firestore.collection("Product").document().set(producto)
        }
    }


}

