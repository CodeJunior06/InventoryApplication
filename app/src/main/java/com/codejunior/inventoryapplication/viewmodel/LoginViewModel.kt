package com.codejunior.inventoryapplication.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.model.LoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginModel: LoginModel): ViewModel() {
    @SuppressLint("StaticFieldLeak")
    private var context:Context?  = null
    private val _isToast: MutableLiveData<String> = MutableLiveData()
    private val _navigate:MutableLiveData<Boolean> = MutableLiveData()
    val isToast:LiveData<String>  get() =  _isToast
    val navigate:LiveData<Boolean> get() = _navigate

     fun initAuthentication(email:String, pass:String){

        val hilo =  viewModelScope.launch {
             if(validFieldNotEmpty(email,pass) && loginModel.initSession(email, pass)){
                 _navigate.value = true
             }else{
                 _isToast.value = context!!.getString(R.string.err_firebase)
             }
         }
         println("HILO ${hilo.isActive}")
         println("HILO ${hilo.isCancelled}")
         println("HILO ${hilo.isCompleted}")
    }

    private fun validFieldNotEmpty(email:String, pass: String) : Boolean{
        if(email.isEmpty() && pass.isEmpty()){
            _isToast.value = context!!.getString(R.string.emptyEmailAndPassword)
            return false
        }
        if(email.isEmpty()){
            _isToast.value = context!!.getString(R.string.emptyEmail)
            return false
        }
        if(pass.isEmpty()){
            _isToast.value = context!!.getString(R.string.emptyPassword)
            return false
        }
        return true
    }

    fun getContext(context: Context){
        this.context = context;
    }
}