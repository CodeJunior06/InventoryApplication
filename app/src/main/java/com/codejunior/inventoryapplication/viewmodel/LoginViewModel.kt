package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import com.codejunior.inventoryapplication.model.LoginModel

class LoginViewModel : BaseViewModel() {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    init {
        if (firebaseController.hasSession()) {
            navigation.value = NAVIGATION.GO_MAIN_VIEW
        }
    }

    fun login() {
        val emailLogin = email.value ?: ""
        val passwordLogin = password.value ?: ""
        try {
            if (emailLogin.isEmpty()
                || passwordLogin.isEmpty()
            ) errores.value = ERROR.EMPTY_FIELDS
            else {
                val model = LoginModel(emailLogin, passwordLogin)
                model.auth({
                    navigation.value = NAVIGATION.GO_MAIN_VIEW
                }, {
                    errores.value = ERROR.WRONG_CREDENTIALS
                })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}