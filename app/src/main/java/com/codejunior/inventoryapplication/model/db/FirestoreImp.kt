package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreImp @Inject constructor(private val authFirebase: FirebaseAuth) :
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


}

