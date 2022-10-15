package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.ProductoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productoModel: ProductoModel) : BaseViewModel() {
    val nameProduct = MutableLiveData("")
    val proveedor = MutableLiveData("")
    val disponibilidad:MutableLiveData<String> = MutableLiveData<String>("")
    val stock = MutableLiveData("")
    val categoria =MutableLiveData("")
    val cost =MutableLiveData("")
    private lateinit var lst:List<String>

    fun validProduct(lst:List<String>){
        if(validEmptyString(lst)){
            viewModelScope.launch {
                if(productoModel.insertProductDB(lst)){
                    success.value = SUCCESS.PRODUCT_REGISTER_COMPLETE
                    navigation.value = NAVIGATION.GO_MAIN_VIEW
                }
            }

        }else{
            errores.value = ERROR.EMPTY_FIELDS
        }
    }

    private fun validEmptyString(lst:List<String>): Boolean {
       return lst.all {
            it.isNotEmpty()
        }
    }

}