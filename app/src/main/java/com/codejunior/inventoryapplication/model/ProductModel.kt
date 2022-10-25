package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend fun insertProductDB(lst: List<String>): Boolean {
        val productsIngress =
            Product(
                productName = lst[0],
                productProvider = lst[1],
                productAvailability = lst[2].toInt(),
                productStock = lst[3].toInt(),
                productCategory = lst[4],
                productCost = lst[5].toInt(),
                productUserID = firebaseRepository.getSession()!!.uid
            )
        firebaseRepository.insertProduct(productsIngress).await()
        return true
    }
}