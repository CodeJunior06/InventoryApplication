package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.db.FirestoreImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val firestoreImp: FirestoreImp): BaseViewModel() {
        private var bool:Boolean = true;
    fun getInitSession(): Boolean {
        viewModelScope.launch{
            if(firestoreImp.getSession()?.uid==null){
                bool = false
            }
        }
        println("UID FIREBASE_AUTH "+firestoreImp.getSession()?.uid)
      return bool
    }


}