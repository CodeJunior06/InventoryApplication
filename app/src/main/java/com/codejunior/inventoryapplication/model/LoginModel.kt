package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirestoreImp
import javax.inject.Inject

class LoginModel @Inject constructor(private val firebaseAuth: FirestoreImp) {

    fun initSession(emai:String,pass:String) : Boolean{
        val user = UserFirebase().also {
            run {
                it.email = emai
                it.pass = pass
            }
        }
        if(firebaseAuth.isSetAuthentication(user)!=null){
            return true
        }
        return false
    }
}