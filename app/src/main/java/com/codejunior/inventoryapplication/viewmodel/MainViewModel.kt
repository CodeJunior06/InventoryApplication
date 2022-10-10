package com.codejunior.inventoryapplication.viewmodel

import com.codejunior.inventoryapplication.model.MainModel

class MainViewModel : BaseViewModel() {

    private val model = MainModel()

    fun logout() {
        model.signOut {
            navigation.value = NAVIGATION.GO_LOGIN_VIEW
        }
    }
}