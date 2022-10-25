package com.codejunior.inventoryapplication.model.db.network.model

import java.util.*

data class Product(
    val productID:String = UUID.randomUUID().toString(),
    val productName: String,
    val productProvider: String,
    val productAvailability: Int,
    val productStock: Int,
    val productCategory: String,
    val productCost: Int,
    val productUserID:String
)
