package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _isToast: MutableLiveData<String> = MutableLiveData()
    val isToast:LiveData<String>  get() =  _isToast


    fun initAuthentication(email:String , pass:String){

        if(email.isEmpty()){

        }
    }
}