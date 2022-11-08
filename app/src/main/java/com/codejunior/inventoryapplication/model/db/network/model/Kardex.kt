package com.codejunior.inventoryapplication.model.db.network.model

import java.util.*

data class Kardex(
    val kardexID: String = UUID.randomUUID().toString(),
    val kardexNameProcess: String,
    val kardexDescription: String,
    val kardexDate: String,
    val kardexUserID: String
)
