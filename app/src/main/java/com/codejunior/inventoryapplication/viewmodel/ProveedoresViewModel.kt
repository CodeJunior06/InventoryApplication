package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.ProveedoresModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.codejunior.inventoryapplication.model.db.model.Provider

@HiltViewModel
class ProveedoresViewModel @Inject constructor(private val modelProvider: ProveedoresModel) : BaseViewModel()  {
    private lateinit var lst:List<Provider>
    val observerList = MutableLiveData<Boolean>()
    fun getDataView(lstData:List<String>){
        if(validEmptyString(lstData)){
            viewModelScope.launch {
              modelProvider.insertProvider(lstData)
                success.value = SUCCESS.PROVIDER_INSERT_COMPLETE
                navigation.value = NAVIGATION.GO_MAIN_VIEW
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

     fun getDataProvider(){
        viewModelScope.launch {

            setListProvider(modelProvider.getAllProvider() )
            observerList.value = true
        }
    }
    fun setListProvider(list:List<Provider>){
        this.lst = list
    }
    fun geyListProvider():List<Provider>{
      return  this.lst
    }
}