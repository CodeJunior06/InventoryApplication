package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.db.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository): BaseViewModel() {
        private var bool:Boolean = true;
    fun getInitSession(): Boolean {
        viewModelScope.launch{
            if(firebaseRepository.getSession()?.uid==null){
                bool = false
            }
        }
        println("UID FIREBASE_AUTH "+firebaseRepository.getSession()?.uid)
      return bool
    }


}