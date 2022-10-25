package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.view.adapter.ButtonAdapter
import com.codejunior.inventoryapplication.databinding.ActivityMainBinding
import com.codejunior.inventoryapplication.utils.extension.*
import com.codejunior.inventoryapplication.utils.extension.intentProviderFromActivity
import com.codejunior.inventoryapplication.view.adapter.model.ButtonData
import com.codejunior.inventoryapplication.viewmodel.Error
import com.codejunior.inventoryapplication.viewmodel.MainViewModel
import com.codejunior.inventoryapplication.viewmodel.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainView : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private val mainViewModel: MainViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.lifecycleOwner = this@MainView
        binding!!.viewModelMain = mainViewModel

        mainViewModel.success.observe(this@MainView) {
            toastMessage(it)
        }
        mainViewModel.error.observe(this@MainView) {
            toastMessage(it)
        }
        mainViewModel.navigation.observe(this@MainView) { navigation ->

            when (navigation) {
                Navigation.GO_LOGIN_VIEW -> {
                    context.startActivity(intentActivityLogin())
                    finish()
                }
                Navigation.GO_PROVIDERS_VIEW -> {
                    context.startActivity(intentProviderFromActivity())
                }
                Navigation.GO_PRODUCTS_VIEW -> {
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentProductFromActivity())
                    }
                    mainViewModel.error.value = Error.ErrorNewUser.message
                }
                Navigation.GO_CATEGORY_VIEW -> {
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentCategoryFromActivity())
                    }
                    mainViewModel.error.value = Error.ErrorNewUser.message
                }
                else -> println()
            }
        }

        binding!!.recyclerButtons.apply {
            adapter = ButtonAdapter(
                listOf(
                    ButtonData(
                        context.getString(R.string.providers),
                        R.drawable.proveedores_icon
                    ) {
                        mainViewModel.navigation.postValue(Navigation.GO_PROVIDERS_VIEW)
                    },
                    ButtonData(
                        context.getString(R.string.categories),
                        R.drawable.categorias_icon
                    ) {
                        mainViewModel.navigation.postValue(Navigation.GO_CATEGORY_VIEW)
                    },
                    ButtonData(context.getString(R.string.products), R.drawable.product_icon) {
                        mainViewModel.navigation.postValue(Navigation.GO_PRODUCTS_VIEW)
                    },
                    ButtonData(context.getString(R.string.sales), R.drawable.ventas_icon) {

                    },
                    ButtonData(context.getString(R.string.kardex), R.drawable.kardex_icon) {

                    })
            )
            layoutManager = LinearLayoutManager(this@MainView)
        }
    }

    private fun isNewUSerApplication(): Boolean {
        if (mainViewModel.isNewUSer()) {
            println("ES NUEVO USUARIO")
            return true
        }
        println("ES VIEJO USUARIO")
        return false
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