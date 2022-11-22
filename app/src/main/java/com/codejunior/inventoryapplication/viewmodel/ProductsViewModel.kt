package com.codejunior.inventoryapplication.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productModel: ProductModel) :
    BaseViewModel() {

    val arrayProviderName = MutableLiveData<List<String>>()
    val arrayCategoryName = MutableLiveData<List<String>>()
    val onLoading = MutableLiveData(true)
    val onLoadingCategory = MutableLiveData(true)
    val onLoadingOrTextView = MutableLiveData<Boolean>()

    fun validProduct(lst: List<String>) {
        if (validEmptyString(lst)) {
            viewModelScope.launch {
                if (productModel.insertProductDB(lst)) {
                    success.value = Success.SuccessInsertProduct.message
                    navigation.value = Navigation.GO_MAIN_VIEW
                }
            }

        } else {
            error.value = Error.ErrorEmpty.message
        }
    }

    private var lstProvider:ArrayList<String> = ArrayList()
    suspend fun getDataProvider(){


        val rta = viewModelScope.async { productModel.getDataAllProvider() }
        for (i in rta.await()){
            lstProvider.add(i)
        }
        arrayProviderName.value = lstProvider

    }

    suspend fun getCategoryByProvider(item: String) {
        val response =  viewModelScope.async {
               productModel.searchCategory(item)
        }
        arrayCategoryName.value = response.await()
    }

    fun setImageURI(it: Uri) {
        productModel.setImageURI(it)
    }

}