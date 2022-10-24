package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityProductsBinding
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsView : AppCompatActivity() {

    private var _binding: ActivityProductsBinding? = null
    private val binding get() = _binding
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.lifecycleOwner = this
        binding!!.viewModelProducts = productsViewModel


        binding!!.button.setOnClickListener {
            val lst = mutableListOf(
                binding!!.edtName.editText?.text.toString(),
                binding!!.edtProvider.editText?.text.toString(),
                binding!!.edtDisponibilidad.editText?.text.toString(),
                binding!!.edtStock.editText?.text.toString(),
                binding!!.edtCategories.editText?.text.toString(),
                binding!!.edtCoste.editText?.text.toString()
            )
            productsViewModel.validProduct(lst)
        }

        productsViewModel.navigation.observe(this) {
            when (it) {
                Navigation.GO_MAIN_VIEW -> {
                    startActivity(Intent(this, MainView::class.java))
                }
                else -> println()
            }
        }

        productsViewModel.error.observe(this) {
            toastMessage(it)
        }

        productsViewModel.success.observe(this) {
            toastMessage(it)
        }
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}