package com.codejunior.inventoryapplication.viewmodel

import android.content.Context
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.job

@HiltViewModel
class LoginViewModel : ViewModel() {

    private val _isToast: MutableLiveData<String> = MutableLiveData()
    val isToast:LiveData<String>  get() =  _isToast


    fun initAuthentication(email:String , pass:String){

        if(email.isEmpty()){
        }
    }
}

/*@Suppress("UNCHECKED_CAST")
class FactoryLogin : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel() as T
    }
}*/