package com.codejunior.inventoryapplication.model.db.model

data class Product(
    val name: String,
    val provider: String,
    val availability: Int,
    val stock: Int,
    val category: String,
    val cost: Int
)
