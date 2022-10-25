package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MainModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun signOut(success: () -> Unit) {
        firebaseRepository.signOut(success)
    }


    suspend fun getUser(): Boolean {
        val userSession = firebaseRepository.getSession()!!.uid
        val response = firebaseRepository.getAllUserTable().await()

        for (user in response) {

            if (userSession == user.getString("id") && user.getBoolean("isNew") == true) {
                userApplication.isNew = true
                userApplication.id = user.id
                return true
            }
        }
        return false
    }
}