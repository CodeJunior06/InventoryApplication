package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityLoginBinding
import com.codejunior.inventoryapplication.viewmodel.ERROR
import com.codejunior.inventoryapplication.viewmodel.LoginViewModel
import com.codejunior.inventoryapplication.viewmodel.NAVIGATION
import com.codejunior.inventoryapplication.viewmodel.SUCCESS
import com.proyeto.medicineapp.data.extensionfunctions.toast

class LoginView : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelLogin = loginViewModel

        loginViewModel.errores.observe(this) {
            when (it) {
                ERROR.EMPTY_FIELDS -> {
                    toast("No deje campos vacios")
                }
                ERROR.WRONG_CREDENTIALS -> {
                    toast("credenciales invalidas")
                }
            }
        }
        loginViewModel.success.observe(this) {
            when (it) {
                SUCCESS.LOGIN_SUCCES -> {
                    toast("login correcto")
                }
            }
        }
        loginViewModel.navigation.observe(this) {
            when (it) {
                NAVIGATION.GO_MAIN_VIEW -> {
                    val intent = Intent(context, MainView::class.java)
                    context.startActivity(intent)
                    finish()
                }
            }
        }
    }
}