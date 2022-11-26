package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import javax.inject.Inject

class MainModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun signOut() {
        firebaseRepository.signOut()
    }
}