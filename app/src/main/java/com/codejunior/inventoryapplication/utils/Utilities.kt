package com.codejunior.inventoryapplication.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.model.ProviderModel
import com.codejunior.inventoryapplication.model.ProviderModel.Module.*
import com.codejunior.inventoryapplication.model.db.network.model.Category
import com.codejunior.inventoryapplication.model.db.network.model.Product
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

        fun getDescription(
            name: ProviderModel.Module,
            model: Any,
            action: ProviderModel.Action
        ): String {

            return when (name) {
                PROVIDER -> {
                    model as Provider
                    when (action) {
                        ProviderModel.Action.INSERT -> {
                            "Se agrego un nuevo proveedor llamado ${model.providerName}"
                        }
                        ProviderModel.Action.UPDATE -> {
                            "Se actulizaron los datos del proveedor llamado ${model.providerName}"
                        }
                        ProviderModel.Action.DELETE -> {
                            "Se añadio un nuevo proveedor llamado ${model.providerName}"
                        }
                    }

                }
                CATEGORY -> {
                    model as Category
                    when (action) {
                        ProviderModel.Action.INSERT -> {
                            "Se agrego una nueva categoria ${model.categoryName}"
                        }
                        ProviderModel.Action.UPDATE -> {
                            "Se actulizaron los datos del proveedor llamado ${model.categoryName}"
                        }
                        ProviderModel.Action.DELETE -> {
                            "Se añadio un nuevo proveedor llamado ${model.categoryName}"
                        }
                    }
                }
                PRODUCT -> {
                    model as Product
                    when (action) {
                        ProviderModel.Action.INSERT -> {
                            "Se añadio el producto ${model.productName} al inventario"
                        }
                        ProviderModel.Action.UPDATE -> {
                            "Se actulizaron los datos del producto llamado ${model.productName}"
                        }
                        ProviderModel.Action.DELETE -> {
                            "Se elimino el producto llamado ${model.productName}"
                        }
                    }
                }
            }
        }

        fun getColorContainerKardex(name: String,context: Context): Int {
            return when (name) {

                PROVIDER.name.uppercase()-> {
                    ContextCompat.getColor(context, R.color.providerColor)
                }
                CATEGORY.name.uppercase()->{
                    ContextCompat.getColor(context, R.color.categoryColor)
                }
                PRODUCT.name.uppercase()->{
                    ContextCompat.getColor(context, R.color.productColor)
                }else -> 0

            }

        }
    }


}