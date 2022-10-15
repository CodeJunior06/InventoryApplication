package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.Producto
import com.codejunior.inventoryapplication.model.db.FirestoreImp
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductoModel @Inject constructor(private val  firestoreImp: FirestoreImp) {

    suspend fun insertProductDB(lst:List<String>) :Boolean{
        val productsIngress = Producto(lst[0],lst[1],lst[2].toInt(),lst[3].toInt(),lst[4],lst[5].toInt())
        firestoreImp.insertProduct(productsIngress).await()
        return true
    }
}