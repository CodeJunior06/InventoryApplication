package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Category
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CategoryModel @Inject constructor(private val  firebaseRepository:FirebaseRepository){
    private var lstProvider: ArrayList<Provider> = ArrayList()
    suspend fun insertData(nameCategory:String,provider:String) {
       val model = Category(
           categoryName = nameCategory,
           categoryProviderID = provider,
           categoryUserID = firebaseRepository.getSession()!!.uid
       )
        firebaseRepository.insertCategory(model).await()
        if(userApplication.isNew){
            firebaseRepository.updateUserTable().await()
            userApplication.isNew = false
        }
    }

    suspend fun getDataAllProvider() : ArrayList<Provider>{
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
                    providerAddress = model.getString(NameFirebase.FIELD_PROVIDER_ADDRESS)!!,
                    providerUserID =  model.getString(NameFirebase.FIELD_PROVIDER_USER_ID)!!,
                )
            )
        }
        return lstProvider
    }
}