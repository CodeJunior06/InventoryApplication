package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.adapter.ButtonAdapter
import com.codejunior.inventoryapplication.adapter.ButtonData
import com.codejunior.inventoryapplication.databinding.ActivityMainBinding

class MainView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {

            recyclerButtons.apply {
                adapter = ButtonAdapter(listOf(
                    ButtonData(context.getString(R.string.providers), R.drawable.proveedores_icon),
                    ButtonData(context.getString(R.string.categories), R.drawable.categorias_icon),
                    ButtonData(context.getString(R.string.products), R.drawable.product_icon),
                    ButtonData(context.getString(R.string.sales), R.drawable.ventas_icon),
                    ButtonData(context.getString(R.string.kardex), R.drawable.kardex_icon)))
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