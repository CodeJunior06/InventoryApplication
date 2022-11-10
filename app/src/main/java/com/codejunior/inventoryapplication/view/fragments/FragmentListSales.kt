package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.FragmentListSalesBinding
import com.codejunior.inventoryapplication.viewmodel.SaleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentListSales : Fragment() {

    private var _binding:FragmentListSalesBinding? = null
    private val binding get() = _binding

    private val salesViewModel:SaleViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListSalesBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}