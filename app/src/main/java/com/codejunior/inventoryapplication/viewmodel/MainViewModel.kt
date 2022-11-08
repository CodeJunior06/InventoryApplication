package com.codejunior.inventoryapplication.viewmodel

import com.codejunior.inventoryapplication.model.MainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val model: MainModel) : BaseViewModel() {
    fun logout() {
        model.signOut()
        navigation.value = Navigation.GO_LOGIN_VIEW
    }
}