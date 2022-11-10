package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.codejunior.inventoryapplication.databinding.ActivitySaleViewBinding
import com.codejunior.inventoryapplication.viewmodel.SaleViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SaleView : AppCompatActivity() {

    private var _binding:ActivitySaleViewBinding? = null
    private val binding get() = _binding

    private val saleViewModel:SaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding  = ActivitySaleViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


    }

    override fun onResume() {
        super.onResume()
        ManageSystemUI.hideSystemUI(window)
    }
}