package com.codejunior.inventoryapplication.model.db.network.constants

import javax.inject.Singleton

@Singleton
class NameFirebase {

    companion object {
        //Tablas
        const val TABLE_PRODUCT: String = "product"
        const val TABLE_PROVIDER: String = "provider"
        const val TABLE_CATEGORY: String = "category"
        const val TABLE_KARDEX: String = "kardex"
        const val TABLE_USER: String = "user"

        //Provider
        const val FIELD_PROVIDER_ID: String = "providerID"
        const val FIELD_PROVIDER_NAME: String = "providerName"
        const val FIELD_PROVIDER_TYPE_DOCUMENT: String = "providerTypeDocument"
        const val FIELD_PROVIDER_DOCUMENT: String = "providerDocument"
        const val FIELD_PROVIDER_PHONE: String = "providerPhone"
        const val FIELD_PROVIDER_EMAIL: String = "providerEmail"
        const val FIELD_PROVIDER_ADDRESS: String = "providerAddress"
        const val FIELD_PROVIDER_USER_ID: String = "providerUserID"

        //Category
        const val FIELD_CATEGORY_ID: String = "categoryID"
        const val FIELD_CATEGORY_NAME: String = "categoryName"
        const val FIELD_CATEGORY_PROVIDER_ID: String = "categoryProviderID"
        const val FIELD_CATEGORY_USER_ID: String = "categoryUserID"

        //Product
        const val FIELD_PRODUCT_ID: String = "productID"
        const val FIELD_PRODUCT_NAME: String = "productName"
        const val FIELD_PRODUCT_PROVIDER: String = "productProvider"
        const val FIELD_PRODUCT_AVAILABILITY: String = "productAvailability"
        const val FIELD_PRODUCT_STOCK: String = "productStock"
        const val FIELD_PRODUCT_CATEGORY: String = "productCategory"
        const val FIELD_PRODUCT_COST: String = "productCost"
        const val FIELD_PRODUCT_USER_ID: String = "productUserID"

    }


}