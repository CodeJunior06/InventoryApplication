package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirebaseRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ActivityRetainedScoped
class LoginModel @Inject constructor(private val firebaseAuth: FirebaseRepository) {
    suspend fun initSession(email: String, pass: String): Boolean {

        val user = UserFirebase().also {
            run {
                it.email = email
                it.pass = pass
            }
        }

        kotlin.runCatching {
            if (firebaseAuth.isSetAuthentication(user).await().user != null) {
                return true
            }
        }.onSuccess {
            return true
        }.onFailure {
            println(it.message)
            return false
        }
        println("A LA VERGA")
        return false
    }

}