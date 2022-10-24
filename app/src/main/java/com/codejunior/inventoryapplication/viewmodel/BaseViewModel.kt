package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val error: MutableLiveData<String> = MutableLiveData()
    val success: MutableLiveData<String> = MutableLiveData()
    val navigation: MutableLiveData<Navigation> = MutableLiveData(null)
}

enum class Navigation {
    GO_MAIN_VIEW,
    GO_LOGIN_VIEW,
    GO_PROVIDERS_VIEW,
    GO_PRODUCTS_VIEW,
    GO_EMPTY
}

sealed class Success(val message: String) {
    object SuccessLogin : Success("Credential Success")
    object SuccessLogOut : Success("Exit System")
    object SuccessInsertProduct : Success("Product Create Correct")
    object SuccessInsertProvider : Success("Provider Create Correct")
}

sealed class Error(val message: String) {
    object ErrorEmpty : Error("Field Empty, Please Insert")
    object ErrorCredential : Error("Error Credential")
}