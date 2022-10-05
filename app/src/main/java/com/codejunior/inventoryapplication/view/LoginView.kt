package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityLoginBinding
import com.codejunior.inventoryapplication.utils.extension.intentActivityMain
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

@AndroidEntryPoint
class LoginView : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel.getContext(this)
        binding.billBack.setOnClickListener {


            loginViewModel.initAuthentication(
                binding.txtInputMail.editText!!.text.toString(),
                binding.txtInputPass.editText!!.text.toString()
            )
        }
        loginViewModel.isToast.observe(this) { error ->
            toastMessage(error)
        }
        loginViewModel.navigate.observe(this) {
            if (it) {
                startActivity(intentActivityMain())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }
}