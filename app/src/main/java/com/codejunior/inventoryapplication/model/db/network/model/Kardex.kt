package com.codejunior.inventoryapplication.model.db.network.model

import java.io.Serializable
import java.util.*

data class Kardex(
    @JvmField
    val kardexID: String = UUID.randomUUID()?.toString() ?: "",
    @JvmField
    val kardexNameProcess: String = "",
    @JvmField
    val kardexDescription: String = "",
    @JvmField
    val kardexDate: String = "",
    @JvmField
    val kardexUserID: String = "",
    @JvmField
    val kardexTypeModule: String = ""
) : Serializable
