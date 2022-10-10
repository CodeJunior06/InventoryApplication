package com.codejunior.inventoryapplication.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.Nullable
import androidx.lifecycle.*
import com.codejunior.inventoryapplication.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(@Nullable private val loginModel: LoginModel?) :
    BaseViewModel() {

    @SuppressLint("StaticFieldLeak")
    private var context: Context? = null
    val password: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")


    fun initAuthentication() {

        val hilo = viewModelScope.launch {
            if (validFieldNotEmpty(
                    email.value.toString(),
                    password.value.toString()
                ) && loginModel!!.initSession(email.value.toString(), password.value.toString())
            ) {
                navigation.value = NAVIGATION.GO_MAIN_VIEW
            } else {
                errores.value = ERROR.WRONG_CREDENTIALS
            }
        }
        println("HILO ${hilo.isActive}")
        println("HILO ${hilo.isCancelled}")
        println("HILO ${hilo.isCompleted}")
    }

    private fun validFieldNotEmpty(email: String, pass: String): Boolean {
        if (email.isEmpty() && pass.isEmpty()) {
            errores.value = ERROR.EMPTY_FIELDS
            return false
        }
        if (email.isEmpty()) {
            errores.value = ERROR.EMPTY_FIELDS
            return false
        }
        if (pass.isEmpty()) {
            errores.value = ERROR.EMPTY_FIELDS
            return false
        }
        return true
    }

    fun getContext(context: Context) {
        this.context = context;
    }
}