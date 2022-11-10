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

    private val kardexViewModel: KardexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKardexViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        kardexViewModel.getKardex()

        kardexViewModel.getDataKardex.observe(this) {
            binding!!.recyclerKardex.layoutManager = LinearLayoutManager(this)
            binding!!.recyclerKardex.adapter = KardexAdapter(it)

            kardexViewModel.viewLoading.postValue(false)
        }
        kardexViewModel.viewLoading.observe(this) {
            if (it) {
                binding!!.constraintLoading.visibility = View.VISIBLE
                binding!!.constraintKardex.visibility = View.GONE

            } else {
                binding!!.constraintLoading.visibility = View.GONE
                binding!!.constraintKardex.visibility = View.VISIBLE
            }
        }

    }

    override fun onResume() {
        super.onResume()
        ManageSystemUI.hideSystemUI(window)
    }
}