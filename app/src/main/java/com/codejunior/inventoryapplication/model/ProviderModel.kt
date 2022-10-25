package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProviderModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    private var lst: ArrayList<Provider> = ArrayList()
    suspend fun insertProvider(lstData: List<String>) {
        val modelProvider = Provider(
            providerName = lstData[0],
            providerTypeDocument = lstData[1],
            providerDocument = lstData[2],
            providerPhone = lstData[3],
            providerEmail = lstData[4],
            providerAddress = lstData[5],
            providerUserID =  firebaseRepository.getSession()!!.uid
        )
        firebaseRepository.insertProvider(modelProvider).await()
    }

    suspend fun getAllProvider(): List<Provider> {
        lst.clear()
        val response = firebaseRepository.getAllProviderFB().await()
        for (model in response) {
            println("MODEL DB " + model.get("providerName"))
            lst.add(
                Provider(
                    providerName = model.getString(NameFirebase.FIELD_PROVIDER_NAME)!!,
                    providerTypeDocument = model.getString(NameFirebase.FIELD_PROVIDER_TYPE_DOCUMENT)!!,
                    providerDocument = model.getString(NameFirebase.FIELD_PROVIDER_DOCUMENT)!!,
                    providerPhone = model.getString(NameFirebase.FIELD_PROVIDER_PHONE)!!,
                    providerEmail = model.getString(NameFirebase.FIELD_PROVIDER_EMAIL)!!,
                    providerAddress = model.getString(NameFirebase.FIELD_PROVIDER_ADDRESS)!!,
                    providerUserID =  model.getString(NameFirebase.FIELD_PROVIDER_USER_ID)!!,
                )
            )
        }
        return lst
    }
}