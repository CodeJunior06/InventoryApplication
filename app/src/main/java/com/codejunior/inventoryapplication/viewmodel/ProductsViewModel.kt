package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productModel: ProductModel) :
    BaseViewModel() {

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

    private fun validEmptyString(lst: List<String>): Boolean {
        return lst.all {
            it.isNotEmpty()
        }
    }

}