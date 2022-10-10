package com.codejunior.inventoryapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class FactoryLogin @Inject constructor() : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(null) as T
    }
}
