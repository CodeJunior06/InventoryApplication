package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ActivityProductsBinding
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsView : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var _binding: ActivityProductsBinding? = null
    private val binding get() = _binding
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        CoroutineScope(Dispatchers.Main).launch {
            productsViewModel.getDataProvider()
        }
        productsViewModel.arrayProviderName.observe(this) {
            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, it)
            binding!!.dropdown.setAdapter(arrayAdapter)
            binding!!.dropdown.onItemClickListener = this@ProductsView
            productsViewModel.onLoading.postValue(false)
        }

        productsViewModel.onLoading.observe(this) {
            if (it) {
                binding!!.constraintLayoutLoading.visibility = View.VISIBLE
                binding!!.constraintLayoutField.visibility = View.GONE

            } else {
                binding!!.constraintLayoutLoading.visibility = View.GONE
                binding!!.constraintLayoutField.visibility = View.VISIBLE
            }
        }

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

        productsViewModel.onLoadingCategory.observe(this){
            if(it){
                binding!!.linearCategory.visibility = View.GONE
            }else{
                binding!!.linearCategory.visibility = View.VISIBLE
            }
        }

        productsViewModel.arrayCategoryName.observe(this){
            val arrayAdapterCategory = ArrayAdapter(this, R.layout.dropdown_item, it)
            binding!!.dropdownCategory.setAdapter(arrayAdapterCategory)
            productsViewModel.onLoadingOrTextView.value =true
        }

        productsViewModel.onLoadingOrTextView.observe(this){
            if(it){
                binding!!.lottieLoading.visibility = View.GONE
                binding!!.edtCategories.visibility = View.VISIBLE
                binding!!.lottieLoading.repeatMode
            }else{
                binding!!.lottieLoading.visibility = View.VISIBLE
                binding!!.edtCategories.visibility = View.GONE
            }
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        CoroutineScope(Dispatchers.Main).launch {
            productsViewModel.getCategoryByProvider(item)
        }

        productsViewModel.onLoadingOrTextView.postValue(false)
        productsViewModel.onLoadingCategory.postValue(false)
    }

}