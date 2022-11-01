package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.codejunior.inventoryapplication.databinding.ActivityLoginBinding
import com.codejunior.inventoryapplication.utils.extension.intentRegisterFromActivity
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.*
import com.codejunior.inventoryapplication.window.WindowUtils
import com.codejunior.inventoryapplication.window.WindowUtils.Companion.showSoftKeyboardSetup
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginView : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

    private var _binding: ActivityLoginBinding? = null
    private val binding get() =  _binding!!
    private val loginViewModel: LoginViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelLogin = loginViewModel

        showSoftKeyboardSetup(binding.root)

        loginViewModel.error.observe(this) {
            toastMessage(it)
        }
        loginViewModel.success.observe(this) {
            toastMessage(it)
        }
        loginViewModel.navigation.observe(this) {
            when (it) {
                Navigation.GO_MAIN_VIEW -> {
                    val intent = Intent(context, MainView::class.java)
                    context.startActivity(intent)
                    finish()
                }
                Navigation.GO_REGISTER_VIEW -> startActivity(intentRegisterFromActivity())
                else -> println()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}