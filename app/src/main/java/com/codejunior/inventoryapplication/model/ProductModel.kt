package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    private var lstProvider: ArrayList<String> = ArrayList()
    private var lstCategory: ArrayList<String> = ArrayList()
    suspend fun insertProductDB(lst: List<String>): Boolean {
        val productsIngress =
            Product(
                productName = lst[0],
                productProvider = lst[1],
                productTotal = lst[2].toInt(),
                productAvailability = lst[3].toInt(),
                productStock = lst[4].toInt(),
                productCategory = lst[5],
                productCost = lst[6].toLong(),
                productUserID = firebaseRepository.getSession()!!.uid
            )
        firebaseRepository.insertProduct(productsIngress).await()
        return true
    }

    suspend fun getDataAllProvider() : ArrayList<String> {
        val response = firebaseRepository.getAllProviderByUser().await()
        for (model in response) {
            println("MODEL DB " + model.get("providerName"))
            lstProvider.add(
                    model.getString(NameFirebase.FIELD_PROVIDER_NAME)!!,
                )
        }
        return lstProvider

    }

    suspend fun searchCategory(item: String) : ArrayList<String>{
        lstCategory.clear()
        val response = firebaseRepository.getCategoryByProvider(item).await()
        for (model in response) {
            println("MODEL DB CATEGORY " + model.get("categoryName"))
            lstCategory.add(
                model.getString(NameFirebase.FIELD_CATEGORY_NAME)!!
            )
        }
        return lstCategory

    }
}