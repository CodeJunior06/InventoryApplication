package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.ProviderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.codejunior.inventoryapplication.model.db.model.Provider

@HiltViewModel
class ProviderViewModel @Inject constructor(private val modelProvider: ProviderModel) :
    BaseViewModel() {

    private lateinit var lst: List<Provider>
    val observerList = MutableLiveData<Boolean>()

    fun getDataView(lstData: List<String>) {

        if (validEmptyString(lstData)) {
            viewModelScope.launch {
                modelProvider.insertProvider(lstData)
                success.value = Success.SuccessInsertProvider.message
                navigation.value = Navigation.GO_MAIN_VIEW
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

    fun getDataProvider() {
        viewModelScope.launch {
            lst = emptyList()
            setListProvider(modelProvider.getAllProvider())
            observerList.value = true
        }
    }

    fun setListProvider(list: List<Provider>?) {
        this.lst = list ?: emptyList()
    }

    fun getListProvider(): List<Provider> {
        return lst
    }
}