package com.codejunior.inventoryapplication.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.LoginModel
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginModel: LoginModel) :
    BaseViewModel() {

    @SuppressLint("StaticFieldLeak")
    val password: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")

    private val _password: String by lazy { password.value.toString() }
    private val _email: String by lazy { email.value.toString() }

    fun initAuthentication() {
        password
        email
        viewModelScope.launch {

            if (!validFieldNotEmpty(_email, _password)) {
                error.value = Error.ErrorEmpty.message
                return@launch
            }

            if (loginModel.initSession(_email, _password)) {
                val model =
                    withContext(Dispatchers.Default) { loginModel.getDataUserTable() }
                userApplication.isNew = model.getBoolean(NameFirebase.FIELD_USER_IS_NEW)!!
                userApplication.id = model.getString(NameFirebase.FIELD_USER_ID)!!
                success.value = Success.SuccessLogin.message
                navigation.value = Navigation.GO_MAIN_VIEW
            } else {
                error.value = Error.ErrorCredential.message
            }

        }
    }

    fun goToRegisterUSer(){
        navigation.value = Navigation.GO_REGISTER_VIEW
    }

    private fun validFieldNotEmpty(email: String, pass: String): Boolean {
        val lstTemporal = mutableListOf(email, pass)

        lstTemporal.forEach {
            if (it.isEmpty()) {
                return false
            }
        }
        return true
    }

}