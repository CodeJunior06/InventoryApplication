package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.databinding.FragmentKardexProductBinding
import com.codejunior.inventoryapplication.view.adapter.ProductAdapter
import com.codejunior.inventoryapplication.viewmodel.KardexViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentKardexProduct.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class FragmentKardexProduct : Fragment() {

    private var _binding: FragmentKardexProductBinding? = null
    private val binding get() = _binding
    private val kardexViewModel: KardexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentKardexProductBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kardexViewModel.getAllProduct()

        kardexViewModel.arrayProduct.observe(viewLifecycleOwner) {
            binding!!.recyclerKardexProduct.layoutManager = LinearLayoutManager(context)
            binding!!.recyclerKardexProduct.adapter = ProductAdapter(it)

            kardexViewModel.viewLoading.postValue(false)
        }
        kardexViewModel.viewLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding!!.constraintLoading.visibility = View.VISIBLE
                binding!!.constraintKardex.visibility = View.GONE

            } else {
                binding!!.constraintLoading.visibility = View.GONE
                binding!!.constraintKardex.visibility = View.VISIBLE
            }
        }
    }

}