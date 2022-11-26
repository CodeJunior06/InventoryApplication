package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Category
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.codejunior.inventoryapplication.utils.Utilities
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoryModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    private var lstProvider: ArrayList<Provider> = ArrayList()
    suspend fun insertData(nameCategory: String, provider: String) {

        val action = ProviderModel.ClassName.InsertCategory

        val modelCategory = Category(
            categoryName = nameCategory,
            categoryProviderID = provider,
            categoryUserID = firebaseRepository.getSession()!!.uid
        )

        val modelKardex = Kardex(
            kardexNameProcess = action.title.message,
            kardexDescription = Utilities.getDescription(
                action.module,
                modelCategory,
                action.action
            ),
            kardexDate = Utilities.getDateNow(),
            kardexUserID = firebaseRepository.getSession()!!.uid,
            kardexTypeModule = action.module.name.uppercase()
        )

        firebaseRepository.registerProcessKardex(modelKardex)
        firebaseRepository.insertCategory(modelCategory).await()

        if (userApplication.isNew) {
            firebaseRepository.updateUserTable().await()
            userApplication.isNew = false
        }
    }

    suspend fun getDataAllProvider(): ArrayList<Provider> {
        val response = firebaseRepository.getAllProviderByUser().await()
        for (model in response) {
            println("MODEL DB " + model.get("providerName"))
            lstProvider.add(
                Provider(
                    providerName = model.getString(NameFirebase.FIELD_PROVIDER_NAME)!!,
                    providerTypeDocument = model.getString(NameFirebase.FIELD_PROVIDER_TYPE_DOCUMENT)!!,
                    providerDocument = model.getString(NameFirebase.FIELD_PROVIDER_DOCUMENT)!!,
                    providerPhone = model.getString(NameFirebase.FIELD_PROVIDER_PHONE)!!,
                    providerEmail = model.getString(NameFirebase.FIELD_PROVIDER_EMAIL)!!,
                    providerCompany = model.getString(NameFirebase.FIELD_PROVIDER_COMPANY)!!,
                    providerUserID = model.getString(NameFirebase.FIELD_PROVIDER_USER_ID)!!,
                )
            )
        }
        return lstProvider
    }
}