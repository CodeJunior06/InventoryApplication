package com.codejunior.inventoryapplication.utils

import com.codejunior.inventoryapplication.model.db.network.model.Provider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Singleton
class Utilities {

    companion object {
        @JvmStatic
        fun getDateNow(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return LocalDateTime.now().format(formatter)
        }

        fun getDescription(name: String, model: Provider): String {

            return when (name) {
                "Provider" -> {
                    "Se añadio un nuevo proveedor llamado ${model.providerName}"
                }
                else -> ""
            }
        }
    }


}