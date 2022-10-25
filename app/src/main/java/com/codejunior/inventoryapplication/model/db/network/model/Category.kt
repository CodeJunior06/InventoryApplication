package com.codejunior.inventoryapplication.model.db.network.model

import java.util.*

data class Category(
    val categoryID:String = UUID.randomUUID().toString(),
    val categoryName:String,
    val categoryProviderID:String,
    val categoryUserID:String
)