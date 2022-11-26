package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.InventoryApplication.Companion.userApplication
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.view.adapter.ButtonAdapter
import com.codejunior.inventoryapplication.databinding.ActivityMainBinding
import com.codejunior.inventoryapplication.utils.extension.*
import com.codejunior.inventoryapplication.utils.extension.intentProviderFromActivity
import com.codejunior.inventoryapplication.view.adapter.model.ButtonData
import com.codejunior.inventoryapplication.view.fragments.FragmentDialog
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
                    context.startActivity(intentLoginFromActivity())
                    finish()
                }
                Navigation.GO_PROVIDERS_VIEW -> {
                    context.startActivity(intentProviderFromActivity())
                    finish()
                }
                Navigation.GO_PRODUCTS_VIEW -> {
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentProductFromActivity())
                        return@observe
                    }
                    mainViewModel.error.value = Error.ErrorNewUser.message
                }
                Navigation.GO_CATEGORY_VIEW -> {
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentCategoryFromActivity())
                        return@observe
                    }
                    mainViewModel.error.value = Error.ErrorNewUser.message
                }
                Navigation.GO_KARDEX_VIEW ->{
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentKardexFromActivity())
                        return@observe
                    }
                    mainViewModel.error.value = Error.ErrorNewUser.message
                }
                Navigation.GO_SALE_VIEW ->{
                    if (!isNewUSerApplication()) {
                        context.startActivity(intentSaleFromActivity())
                        return@observe
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
                        FragmentDialog(onResponse = {
                            if(it){
                                mainViewModel.navigation.postValue(Navigation.GO_PRODUCTS_VIEW)
                                return@FragmentDialog
                            }
                            mainViewModel.navigation.postValue(Navigation.GO_KARDEX_VIEW)
                        }).show(supportFragmentManager,"")

                    },
                    ButtonData(context.getString(R.string.sales), R.drawable.ventas_icon) {
                        mainViewModel.navigation.postValue(Navigation.GO_SALE_VIEW)

                    },
                    ButtonData(context.getString(R.string.kardex), R.drawable.kardex_icon) {
                        mainViewModel.navigation.postValue(Navigation.GO_KARDEX_VIEW)
                    })
            )
            layoutManager = LinearLayoutManager(this@MainView)
        }
    }

    private fun isNewUSerApplication(): Boolean {
        if (userApplication.isNew) {
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