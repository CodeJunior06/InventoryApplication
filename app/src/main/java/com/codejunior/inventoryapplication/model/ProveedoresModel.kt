package com.codejunior.inventoryapplication.model

import com.codejunior.inventoryapplication.model.db.FirestoreImp
import com.codejunior.inventoryapplication.model.db.model.Provider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProveedoresModel @Inject constructor(private val  firestoreImp: FirestoreImp) {
        private  var lst:ArrayList<Provider> = ArrayList()
    suspend fun insertProvider(lstData:List<String>){
        val modelProvider = Provider(
            lstData[0],
            lstData[1],
            lstData[2],
            lstData[3],
            lstData[4],
            lstData[5]
        )
        firestoreImp.insertProvider(modelProvider).await()
    }

    suspend fun  getAllProvider():List<Provider>{

      val response =  firestoreImp.getAllProviderFB().await()
       for (model in response){
           println("MODEL DB "+model.get("nameProvider"))
           lst.add(
               Provider(
                   model.get("nameProvider").toString(),
                   model.get("typeDocumentProvider").toString(),
                   model.get("documentProvider").toString(),
                   model.get("phoneProvider").toString(),
                   model.get("emailProvider").toString(),
                   model.get("addressProvider").toString(),
               ))
       }
        return lst
    }
}