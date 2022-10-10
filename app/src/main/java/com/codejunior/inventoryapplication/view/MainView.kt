package com.codejunior.inventoryapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.adapter.ButtonAdapter
import com.codejunior.inventoryapplication.adapter.ButtonData
import com.codejunior.inventoryapplication.databinding.ActivityMainBinding
import com.codejunior.inventoryapplication.viewmodel.MainViewModel
import com.codejunior.inventoryapplication.viewmodel.NAVIGATION
import com.codejunior.inventoryapplication.viewmodel.SUCCESS
import com.proyeto.medicineapp.data.extensionfunctions.toast

class MainView : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainView
            viewModelMain = mainViewModel

            mainViewModel.success.observe(this@MainView) { success ->
                when (success) {
                    SUCCESS.LOG_OUT_SUCCESS -> {
                        toast("sesion cerrada correctamente")
                    }
                }
            }
            mainViewModel.navigation.observe(this@MainView) { navigation ->
                when (navigation) {
                    NAVIGATION.GO_LOGIN_VIEW -> {
                        val intent = Intent(context, LoginView::class.java)
                        context.startActivity(intent)
                        finish()
                    }
                    NAVIGATION.GO_PROVIDERS_VIEW -> {
                        val intent = Intent(context, ProviderView::class.java)
                        context.startActivity(intent)
                    }
                    NAVIGATION.GO_PRODUCTS_VIEW -> {
                        val intent = Intent(context, ProductsView::class.java)
                        context.startActivity(intent)
                    }
                }
            }

            recyclerButtons.apply {
                adapter = ButtonAdapter(listOf(
                    ButtonData(context.getString(R.string.providers), R.drawable.proveedores_icon) {
                        mainViewModel.navigation.postValue(NAVIGATION.GO_PROVIDERS_VIEW)
                    },
                    ButtonData(context.getString(R.string.categories), R.drawable.categorias_icon) {

                    },
                    ButtonData(context.getString(R.string.products), R.drawable.product_icon) {
                        mainViewModel.navigation.postValue(NAVIGATION.GO_PRODUCTS_VIEW)
                    },
                    ButtonData(context.getString(R.string.sales), R.drawable.ventas_icon) {

                    },
                    ButtonData(context.getString(R.string.kardex), R.drawable.kardex_icon) {

                    }))
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