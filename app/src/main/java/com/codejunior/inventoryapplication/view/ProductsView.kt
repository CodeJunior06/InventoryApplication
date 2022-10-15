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
    private lateinit var binding: ActivityProductsBinding

    private val productsViewModel: ProductsViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModelProducts = productsViewModel
        productsViewModel.navigation.value = NAVIGATION.GO_EMPTY
        binding.button.setOnClickListener {
            val lst = mutableListOf(
                binding.edtName.editText?.text.toString(),
                binding.edtProvider.editText?.text.toString(),
                binding.edtDisponibilidad.editText?.text.toString(),
                binding.edtStock.editText?.text.toString(),
                binding.edtCategories.editText?.text.toString(),
                binding.edtCoste.editText?.text.toString()
            )
            productsViewModel.validProduct(lst)
        }
        productsViewModel.navigation.observe(this, {
            when (it) {
                NAVIGATION.GO_MAIN_VIEW -> {
                    startActivity(Intent(this, MainView::class.java))
                }
            }
        })
        productsViewModel.errores.observe(this, {
            when (it) {
                ERROR.EMPTY_FIELDS -> {
                    toastMessage("EMPTY FIELD")
                }
            }
        })

        productsViewModel.success.observe(this, {
            when (it) {

                SUCCESS.PRODUCT_REGISTER_COMPLETE -> {
                    toastMessage("SE INGRESO CORRECTAMENTE EL PRODUCTO")

                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }
}