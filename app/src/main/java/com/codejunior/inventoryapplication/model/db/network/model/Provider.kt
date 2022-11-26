package com.codejunior.inventoryapplication.model.db.network.model

import java.util.*

data class Provider(
    val providerID:String = UUID.randomUUID().toString(),
    val providerName: String,
    val providerTypeDocument: String,
    val providerDocument: String,
    val providerPhone: String,
    val providerEmail: String,
    val providerCompany: String,
    val providerUserID:String
)