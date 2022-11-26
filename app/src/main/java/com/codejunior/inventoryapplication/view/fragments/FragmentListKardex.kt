package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.FragmentListKardexBinding
import com.codejunior.inventoryapplication.view.adapter.KardexAdapter
import com.codejunior.inventoryapplication.viewmodel.KardexViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentListKardex : Fragment() {

    private var _binding: FragmentListKardexBinding? = null
    private val binding get() = _binding
    private val kardexViewModel: KardexViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListKardexBinding.inflate(inflater,container,false)
        return  binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kardexViewModel.getKardex()

        kardexViewModel.getDataKardex.observe(viewLifecycleOwner) {
            binding!!.recyclerKardex.layoutManager = LinearLayoutManager(context)
            binding!!.recyclerKardex.adapter = KardexAdapter(it)

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
        binding!!.viewReportProduct.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentListKardex_to_fragmentKardexProduct)
        }

    }

}