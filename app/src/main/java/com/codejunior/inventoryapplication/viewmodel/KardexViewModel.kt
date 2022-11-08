package com.codejunior.inventoryapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codejunior.inventoryapplication.model.db.network.FirebaseRepository
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.codejunior.inventoryapplication.utils.Utilities
import com.google.firebase.firestore.ktx.toObjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class KardexViewModel @Inject constructor(private val firebaseFirestore: FirebaseRepository) :
    BaseViewModel() {

    val getDataKardex = MutableLiveData<ArrayList<Kardex>>()
    val viewLoading = MutableLiveData(true)

    fun getKardex() {
        viewModelScope.launch(Dispatchers.Main) {
            val response =
                firebaseFirestore.getKardexByDay(Utilities.getDateNow()).await().toObjects<Kardex>()
            getDataKardex.value = response as ArrayList<Kardex>
        }

    }
}