package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirestoreImp
import javax.inject.Inject

class LoginModel @Inject constructor(private val firebaseAuth: FirestoreImp) {
    val ret:Boolean = true
    suspend fun initSession(email:String, pass:String) : Boolean{

        val user = UserFirebase().also {
            run {
                it.email = email
                it.pass = pass
            }
        }
        if(firebaseAuth.isSetAuthentication(user)!!.isSuccessful){
            return true
        }
        return false
    }
}