package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class FirestoreImp @Inject constructor(private val authFirebase: FirebaseAuth) :
    FirebaseRepository {

    private var  result:FirebaseUser?= null

    override suspend fun isSetAuthentication(userFirebase: UserFirebase): FirebaseUser? {


        coroutineScope {
            async {
                result = authFirebase.signInWithEmailAndPassword(userFirebase.email, userFirebase.pass).result.user
            }
        }


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
        println("PASAMO")
        return result
    }

}

