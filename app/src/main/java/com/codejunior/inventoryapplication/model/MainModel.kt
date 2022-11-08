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
}