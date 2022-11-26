package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ActivityKardexViewBinding
import com.codejunior.inventoryapplication.view.adapter.KardexAdapter
import com.codejunior.inventoryapplication.viewmodel.KardexViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KardexView : AppCompatActivity() {
    private var _binding: ActivityKardexViewBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKardexViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    override fun onResume() {
        super.onResume()
        ManageSystemUI.hideSystemUI(window)
    }
}