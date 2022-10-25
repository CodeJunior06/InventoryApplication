package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MainModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun signOut(success: () -> Unit) {
        firebaseRepository.signOut(success)
    }


    suspend fun getUser(): Boolean {
        val userSession = firebaseRepository.getSession()!!.uid
        val response = firebaseRepository.getAllUser().await()

        for (user in response) {

            if (userSession == user.getString("id") && user.getBoolean("isNew") == true) {
                return true
            }
        }
        return false
    }
}