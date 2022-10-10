package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codejunior.inventoryapplication.model.LoginModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class FactoryLogin @Inject constructor(val model:LoginModel) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(model) as T
    }
}
