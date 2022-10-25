package com.codejunior.inventoryapplication.viewmodel

import com.codejunior.inventoryapplication.model.MainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val model: MainModel) : BaseViewModel() {
    fun logout() {
        model.signOut {
            success.value = Success.SuccessLogOut.message
            navigation.value = Navigation.GO_LOGIN_VIEW
        }
    }

    fun isNewUSer(): Boolean {
        return runBlocking {
            model.getUser()
        }
    }
}