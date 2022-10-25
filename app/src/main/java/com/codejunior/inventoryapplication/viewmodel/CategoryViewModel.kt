package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.CategoryModel
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryModel: CategoryModel):BaseViewModel() {
     val arrayProviderName = MutableLiveData<ArrayList<String>>(null)
    private var lstProvider: ArrayList<String> = ArrayList()

    val constraint = MutableLiveData(false)
    fun getDataView(nameCategory:String,provider:String){
        if(nameCategory.isEmpty()&&provider.isEmpty()||(nameCategory.isEmpty()||provider.isEmpty())){
            error.value = Error.ErrorEmpty.message
            return
        }
        viewModelScope.launch {
            categoryModel.insertData(nameCategory,provider)
            navigation.value = Navigation.GO_MAIN_VIEW
        }

    }

    suspend fun getDataProvider(){


        val rta = viewModelScope.async { categoryModel.getDataAllProvider() }
         for (i in rta.await()){
             lstProvider.add(i.providerName)
         }
        arrayProviderName.value = lstProvider

    }


}