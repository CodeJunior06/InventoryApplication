package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MainModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun signOut() {
        firebaseRepository.signOut()
    }


    suspend fun getUser(): Boolean {
        val userSession = firebaseRepository.getSession()!!.uid
        val response = firebaseRepository.getAllUserTable(userSession).await().first()

        if (response.exists()) {
            userApplication.id = response.getString("id")!!
            userApplication.isNew = false
            if (response.getBoolean(NameFirebase.FIELD_USER_IS_NEW) == true) {
                userApplication.isNew = true
                return true
            }
        }
        return false
    }
}