package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: FirebaseRepository): BaseViewModel(){

    val password = MutableLiveData("")
    val email = MutableLiveData("")

    fun sendDataRegister(){
        if (!validFieldNotEmpty(email.value.toString(),password.value.toString())){
            error.value = Error.ErrorEmpty.message
            return
        }
        viewModelScope.launch {
            val response = repository.registerUser(email = email.value.toString(), pass = password.value.toString())
            if(response.user?.uid == null){
                error.value = Error.ErrorRegisterUser.message
                return@launch
            }
            userApplication.id =response.user!!.uid
            userApplication.isNew = true

            repository.signOut()
            success.value = Success.SuccessRegister.message

        }
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

    suspend fun registerUser() {
        repository.registerUserTableFirestore()
    }
}