package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class KardexModel @Inject constructor(private val firebaseFirestore: FirebaseRepository) {
    private var lstProduct: ArrayList<Product> = ArrayList()
    suspend fun getAllProduct() : ArrayList<Product>{
        lstProduct.clear()
        val response =  firebaseFirestore.getAllProduct().await()
        for (model in response){
            lstProduct.add(Product(
                productID =  model.getString(NameFirebase.FIELD_PRODUCT_ID)!!,
                productName = model.getString(NameFirebase.FIELD_PRODUCT_NAME)!!,
                productTotal = model.getLong(NameFirebase.FIELD_PRODUCT_TOTAL_ITEMS)!!,
                productAvailability =  model.getLong(NameFirebase.FIELD_PRODUCT_AVAILABILITY)!!,
                productStock =  model.getLong(NameFirebase.FIELD_PRODUCT_STOCK)!!,
                productCost = model.getLong(NameFirebase.FIELD_PRODUCT_COST)!!,
                productPhoto = model.getString(NameFirebase.FIELD_PRODUCT_PHOTO)!!,
                productProvider = "",
                productUserID = "",
                productCategory = ""
            ))
        }
        return lstProduct
    }
}