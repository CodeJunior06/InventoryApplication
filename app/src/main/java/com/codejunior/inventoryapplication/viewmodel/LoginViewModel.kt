package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel : ViewModel() {

    val adminLogin: MutableLiveData<Boolean> = MutableLiveData()


    fun initAuthentication(){

    }
}

@Suppress("UNCHECKED_CAST")
class FactoryLogin : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}