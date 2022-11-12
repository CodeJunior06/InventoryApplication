package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codejunior.inventoryapplication.databinding.FragmentCreateSalesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCreateSales : Fragment() {


    private var _binding:FragmentCreateSalesBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateSalesBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.helloBlank.setOnClickListener {
            println("1.1. " + findNavController().currentBackStackEntry?.destination?.displayName)
            println("1.2. " +  findNavController().currentDestination?.displayName)
            println("1.3. " +  findNavController().previousBackStackEntry?.destination?.displayName)
            requireActivity().finish()

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        println("FINISH CREATE SALES")
    }


}