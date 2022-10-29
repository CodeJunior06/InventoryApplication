package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Provider
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
                productAvailability = lst[2].toInt(),
                productStock = lst[3].toInt(),
                productCategory = lst[4],
                productCost = lst[5].toInt(),
                productUserID = firebaseRepository.getSession()!!.uid
            )
        firebaseRepository.insertProduct(productsIngress).await()
        return true
    }

    suspend fun getDataAllProvider() : ArrayList<String> {
        val response = firebaseRepository.getAllProviderFB().await()
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