package com.codejunior.inventoryapplication.model

import android.net.Uri
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.model.db.network.model.Product
import com.codejunior.inventoryapplication.utils.Utilities
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductModel @Inject constructor(private val firebaseRepository: FirebaseRepository) {
    private var lstProvider: ArrayList<String> = ArrayList()
    private var lstCategory: ArrayList<String> = ArrayList()
    private lateinit var uri: Uri
    private lateinit var upload:UploadTask.TaskSnapshot

    private lateinit var op: com.google.android.gms.tasks.Task<Uri>
    suspend fun insertProductDB(lst: List<String>): Boolean {
        kotlin.runCatching {
            val action = ProviderModel.ClassName.InsertProduct

            val modelProduct =
                Product(
                    productName = lst[0],
                    productProvider = lst[1],
                    productTotal = lst[2].toInt(),
                    productAvailability = lst[3].toInt(),
                    productStock = lst[4].toInt(),
                    productCategory = lst[5],
                    productCost = lst[6].toLong(),
                    productUserID = firebaseRepository.getSession()!!.uid,
                )

            upload = firebaseRepository.insertImage(uri, modelProduct.productID).await()
            modelProduct.productPhoto = upload.uploadSessionUri.toString()

            val modelKardex = Kardex(
                kardexNameProcess = action.title.message,
                kardexDescription = Utilities.getDescription(
                    action.module,
                    modelProduct,
                    action.action
                ),
                kardexDate = Utilities.getDateNow(),
                kardexUserID = firebaseRepository.getSession()!!.uid,
                kardexTypeModule = action.module.name.uppercase()
            )

            firebaseRepository.registerProcessKardex(modelKardex)

            firebaseRepository.insertProduct(modelProduct)

/*        println("PHOTO ${name.metadata?.md5Hash}")
        println("PHOTO ${name.uploadSessionUri?.port}")
        println("PHOTO ${name.storage.activeDownloadTasks.size}")
        println("PHOTO ${name.storage.activeUploadTasks.size}")
        println("PHOTO ${name.storage.name}")
        println("PHOTO ${name.storage.parent}")
        println("PHOTO ${name.storage.path}")
        println("PHOTO ${name.metadata?.cacheControl}")
        println("PHOTO ${name.uploadSessionUri?.encodedUserInfo}")
        println("PHOTO ${name.uploadSessionUri}")*/


        }.onSuccess {
            println("SUCCESS")
            return true
        }.onFailure {
                println("FAILURE ${it.localizedMessage}")
                println("FAILURE ${it.cause?.localizedMessage}")
                println("FAILURE ${it.stackTrace.first().fileName}")
                println("FAILURE ${it.stackTrace.first().className}")
                println("FAILURE ${it.stackTrace.first().methodName}")
                upload.storage.delete()
            }
        return false
    }

    suspend fun getDataAllProvider(): ArrayList<String> {
        val response = firebaseRepository.getAllProviderByUser().await()
        for (model in response) {
            println("MODEL DB " + model.get("providerName"))
            lstProvider.add(
                model.getString(NameFirebase.FIELD_PROVIDER_NAME)!!,
            )
        }
        return lstProvider

    }

    suspend fun searchCategory(item: String): ArrayList<String> {
        lstCategory.clear()
        val response = firebaseRepository.getCategoryByProvider(item).await()
        for (model in response) {
            println("MODEL DB CATEGORY " + model.get("categoryName"))
            lstCategory.add(
                model.getString(NameFirebase.FIELD_CATEGORY_NAME)!!
            )
        }
        return lstCategory

    }

    fun setImageURI(it: Uri) {
        this.uri = it
    }
}