package com.codejunior.inventoryapplication.viewmodel

import com.codejunior.inventoryapplication.model.MainModel
import com.codejunior.inventoryapplication.model.db.FirestoreImp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor( private val model:MainModel) : BaseViewModel() {

    fun logout() {
        model.signOut {
            navigation.value = NAVIGATION.GO_LOGIN_VIEW
        }
    }
}