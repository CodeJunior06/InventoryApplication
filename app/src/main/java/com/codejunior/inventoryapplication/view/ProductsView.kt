package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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


    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        if ( it != null){
            //La URI se pasa a firestore
            binding!!.itemSelected.setImageURI(it)
        }else{
            Toast.makeText(applicationContext,"SOT SELECT",Toast.LENGTH_LONG).show()
        }
    }

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
                binding!!.edtTotal.editText?.text.toString(),
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

        productsViewModel.onLoadingCategory.observe(this) {
            if (it) {
                binding!!.linearCategory.visibility = View.GONE
            } else {
                binding!!.linearCategory.visibility = View.VISIBLE
            }
        }

        productsViewModel.arrayCategoryName.observe(this) {
            val arrayAdapterCategory = ArrayAdapter(this, R.layout.dropdown_item, it)
            binding!!.dropdownCategory.setAdapter(arrayAdapterCategory)
            productsViewModel.onLoadingOrTextView.value = true
        }

        productsViewModel.onLoadingOrTextView.observe(this) {
            if (it) {
                binding!!.lottieLoading.visibility = View.GONE
                binding!!.edtCategories.visibility = View.VISIBLE
                binding!!.lottieLoading.repeatMode
            } else {
                binding!!.lottieLoading.visibility = View.VISIBLE
                binding!!.edtCategories.visibility = View.GONE
            }
        }

        binding!!.edt2Total.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                println("d "+s)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println("s "+s)
            }

            override fun afterTextChanged(s: Editable?) {
                println("f "+ s.toString())
                kotlin.runCatching {
                    binding!!.edtStock.editText!!.setText(
                        ((s?.toString()
                            ?.toInt() ?: "0".toInt())- (binding?.edt2Disponibilidad?.text?.toString()?.toInt()
                            ?: "0".toInt()) ).toString()
                    )
                }.onFailure {
                    binding!!.edtStock.editText?.setText("0")
                }

            }

        })

        binding!!.edt2Disponibilidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                println()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println()
            }

            override fun afterTextChanged(s: Editable?) {
                kotlin.runCatching {
                    binding!!.edtStock.editText!!.setText(
                        ((binding?.edt2Total?.text?.toString()?.toInt()
                            ?: "0".toInt()) - (s?.toString()
                            ?.toInt() ?: "0".toInt())).toString()
                    )
                }.onFailure {
                    binding!!.edtStock.editText?.setText("0")
                }

            }

        })

        binding!!.uploadPhoto.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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