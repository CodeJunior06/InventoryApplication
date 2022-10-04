package com.codejunior.inventoryapplication.model.db

import com.codejunior.inventoryapplication.model.UserFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


class FirestoreImp @Inject constructor(private val authFirebase: FirebaseAuth) : FirebaseRepository {

    override fun isSetAuthentication(userFirebase: UserFirebase): FirebaseUser? {
        userFirebase.email?.let {
            userFirebase.pass?.let { it1 ->
                authFirebase.signInWithEmailAndPassword(it, it1).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result.user
                        println("PASAMOS POR SUCCESS")
                    } else {
                        println("PASAMOS POR ERROR")
                        return@addOnCompleteListener
                    }
                }

            }
        }
        println("PASAMO")
        return null
    }

}

