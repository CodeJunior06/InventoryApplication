package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.view.adapter.ButtonAdapter
import com.codejunior.inventoryapplication.databinding.ActivityMainBinding
import com.codejunior.inventoryapplication.utils.extension.intentActivityLogin
import com.codejunior.inventoryapplication.utils.extension.intentProductFromActivity
import com.codejunior.inventoryapplication.utils.extension.intentProviderFromActivity
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.view.adapter.model.ButtonData
import com.codejunior.inventoryapplication.viewmodel.MainViewModel
import com.codejunior.inventoryapplication.viewmodel.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainView : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainView
            viewModelMain = mainViewModel

            mainViewModel.success.observe(this@MainView) {
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
                        context.startActivity(intentProductFromActivity())
                    }
                    else -> println()
                }
            }

            recyclerButtons.apply {
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

            setContentView(this.root)
        }
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

}