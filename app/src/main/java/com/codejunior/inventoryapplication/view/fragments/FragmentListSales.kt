package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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
        binding!!.addSaleButton.setOnClickListener {

            println("***. " + findNavController().currentDestination?.displayName)
            println("3. " + findNavController().previousBackStackEntry?.destination?.displayName)

            findNavController().navigate(R.id.action_fragmentListSales_to_fragmentCreateSales)
            findNavController().popBackStack(findNavController().currentDestination!!.id,false)

            println("1. " + findNavController().currentBackStackEntry?.destination?.displayName)
            println("2. " + findNavController().currentDestination?.displayName)
            println("3. " + findNavController().previousBackStackEntry?.destination?.displayName)
            println("4 " + findNavController().navigatorProvider.navigators.values.size)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FINISH LIST SALE")
    }

}