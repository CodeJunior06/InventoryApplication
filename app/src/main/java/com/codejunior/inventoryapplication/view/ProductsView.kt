package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityProductsBinding
import com.codejunior.inventoryapplication.databinding.ActivityProveedoresBinding
import com.codejunior.inventoryapplication.viewmodel.ProductsViewModel
import com.codejunior.inventoryapplication.viewmodel.ProveedoresViewModel

class ProductsView : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding

    private val productsViewModel: ProductsViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelProducts = productsViewModel
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }
}