package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codejunior.inventoryapplication.network.FirebaseController

open class BaseViewModel : ViewModel() {
    val errores : MutableLiveData<ERROR> = MutableLiveData(null)
    val success : MutableLiveData<SUCCESS> = MutableLiveData(null)
    val navigation : MutableLiveData<NAVIGATION> = MutableLiveData(null)
    val firebaseController = FirebaseController()
}

enum class ERROR{
    EMPTY_FIELDS,
    WRONG_CREDENTIALS,
    CANT_REGISTER_MED
}

enum class SUCCESS{
    LOGIN_SUCCES,
    REGISTER_SUCCES,
    LOG_OUT_SUCCESS,
    PRODUCT_REGISTER_COMPLETE,
    PROVIDER_INSERT_COMPLETE
}

enum class NAVIGATION{
    GO_MAIN_VIEW,
    GO_LOGIN_VIEW,
    GO_PROVIDERS_VIEW,
    GO_PRODUCTS_VIEW,
    GO_EMPTY
}