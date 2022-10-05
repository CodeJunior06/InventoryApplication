package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FirestoreImp @Inject constructor(private val authFirebase: FirebaseAuth) :
    FirebaseRepository {

    private var  result: Task<AuthResult>? = null
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
    override suspend fun isSetAuthentication(userFirebase: UserFirebase): Task<AuthResult>? {
        result = null
        withContext(dispatcher) {
            result = authFirebase.signInWithEmailAndPassword(
                userFirebase.email,
                userFirebase.pass
            )
        }
        return result
        /*userFirebase.email?.let {
            userFirebase.pass?.let { it1 ->
                authFirebase.signInWithEmailAndPassword(it, it1).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result.user
                        println("PASAMOS POR SUCCESS")
                    } else {
                        println("PASAMOS POR ERROR")
                    }
                }

            }
        }*/

    }

}

