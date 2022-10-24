package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.model.Product
import com.codejunior.inventoryapplication.model.db.FirebaseRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend fun insertProductDB(lst: List<String>): Boolean {
        val productsIngress =
            Product(lst[0], lst[1], lst[2].toInt(), lst[3].toInt(), lst[4], lst[5].toInt())
        firebaseRepository.insertProduct(productsIngress).await()
        return true
    }
}