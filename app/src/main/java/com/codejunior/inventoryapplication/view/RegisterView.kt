package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.codejunior.inventoryapplication.databinding.ActivityRegisterViewBinding
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterView : AppCompatActivity() {

    private var _binding: ActivityRegisterViewBinding? = null
    private val binding get() = _binding

    private val registerViewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.lifecycleOwner = this
        binding!!.registerViewModel = registerViewModel

        registerViewModel.error.observe(this) {
            toastMessage(it)
        }
        registerViewModel.success.observe(this) {
            toastMessage(it)
            CoroutineScope(Dispatchers.Unconfined).launch{
                registerViewModel.registerUser()
            }
            finish()
            this.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        ManageSystemUI.hideSystemUI(window)
    }
}