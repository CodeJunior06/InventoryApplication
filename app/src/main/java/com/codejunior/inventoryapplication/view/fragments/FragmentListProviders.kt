package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.FragmentListProvidersBinding
import com.codejunior.inventoryapplication.view.adapter.ProviderAdapter
import com.codejunior.inventoryapplication.viewmodel.ProveedoresViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListProviders.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FragmentListProviders : Fragment() {

    private lateinit var _binding: FragmentListProvidersBinding
    val binding: FragmentListProvidersBinding get() = _binding

    private val viewModelProvider: ProveedoresViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListProvidersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelProvider.getDataProvider()

        viewModelProvider.observerList.observe(viewLifecycleOwner) {
            if (it) {
                binding.recyclerProvider.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.recyclerProvider.adapter = ProviderAdapter(viewModelProvider.geyListProvider())
            }

        }


    }
}