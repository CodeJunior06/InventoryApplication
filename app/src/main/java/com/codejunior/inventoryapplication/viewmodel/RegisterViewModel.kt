package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    val adminLogin: MutableLiveData<Boolean> = MutableLiveData()
}