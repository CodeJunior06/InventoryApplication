package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityProveedoresBinding
import com.codejunior.inventoryapplication.viewmodel.ProveedoresViewModel

class ProviderView : AppCompatActivity() {
    private lateinit var binding: ActivityProveedoresBinding

    private val providersViewModel: ProveedoresViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProveedoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelProveedores = providersViewModel
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }
}