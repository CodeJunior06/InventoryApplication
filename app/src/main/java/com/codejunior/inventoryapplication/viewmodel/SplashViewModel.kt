package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.constants.NameFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    BaseViewModel() {

    fun getInitSession() {
        viewModelScope.launch {
            val responseUser = firebaseRepository.getSession()
            if (responseUser?.uid == null) {
                navigation.value = Navigation.GO_LOGIN_VIEW
            } else {
               launch(Dispatchers.Main) {
                   val responseUserTable =
                       firebaseRepository.getAllUserTable(responseUser.uid).await().documents.first()
                   userApplication.id = responseUser.uid
                   userApplication.isNew =
                       responseUserTable?.getBoolean(NameFirebase.FIELD_USER_IS_NEW)!!
                   navigation.value = Navigation.GO_MAIN_VIEW
               }
            }
        }
        println("UID FIREBASE_AUTH " + firebaseRepository.getSession()?.uid)
    }


}