package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.codejunior.inventoryapplication.utils.Utilities.Companion.getDateNow
import com.codejunior.inventoryapplication.utils.Utilities.Companion.getDescription
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProviderModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    private var lst: ArrayList<Provider> = ArrayList()
    suspend fun insertProvider(lstData: List<String>) {

        val obj = ClassName.InsertProvider

        val modelProvider = Provider(
            providerName = lstData[0],
            providerTypeDocument = lstData[1],
            providerDocument = lstData[2],
            providerPhone = lstData[3],
            providerEmail = lstData[4],
            providerCompany = lstData[5],
            providerUserID = firebaseRepository.getSession()!!.uid
        )

        val modelKardex = Kardex(
            kardexNameProcess = obj.title.message,
            kardexDescription = getDescription(obj.module, modelProvider, obj.action),
            kardexDate = getDateNow(),
            kardexUserID = firebaseRepository.getSession()!!.uid,
            kardexTypeModule = obj.module.name.uppercase()
        )
        firebaseRepository.registerProcessKardex(modelKardex)
        firebaseRepository.insertProvider(modelProvider).await()
    }

    suspend fun getAllProvider(): List<Provider> {
        lst.clear()
        val response = firebaseRepository.getAllProviderByUser().await()
        for (model in response) {
            println("MODEL DB " + model.get("providerName"))
            lst.add(
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
        return lst
    }

    enum class Module {
        PROVIDER,
        CATEGORY,
        PRODUCT
    }

    enum class Title(val message: String) {
        INSERT_PROVIDER("Add New Provider"),
        DELETE_PROVIDER("Delete Provider"),
        UPDATE_PROVIDER("Update Provider"),

        INSERT_CATEGORY("Add New Category"),

        INSERT_PRODUCT("Add New Product")
    }

    enum class Action {
        INSERT,
        UPDATE,
        DELETE
    }

    sealed class ClassName(val title: Title, val module: Module, val action: Action) {
        object InsertProvider : ClassName(Title.INSERT_PROVIDER, Module.PROVIDER, Action.INSERT)
        object InsertCategory : ClassName(Title.INSERT_CATEGORY, Module.CATEGORY, Action.INSERT)
        object InsertProduct : ClassName(Title.INSERT_PRODUCT, Module.PRODUCT, Action.INSERT)
    }
}

