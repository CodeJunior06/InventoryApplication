package com.codejunior.inventoryapplication.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.codejunior.inventoryapplication.databinding.FragmentListProvidersBinding
import com.codejunior.inventoryapplication.view.adapter.ProviderAdapter
import com.codejunior.inventoryapplication.viewmodel.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Singleton

@AndroidEntryPoint
@Singleton
class FragmentListProviders : Fragment() {

    private var _binding: FragmentListProvidersBinding? = null
    val binding get() = _binding

    private val viewModelProvider: ProviderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListProvidersBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelProvider.getDataProvider()
        binding!!.recyclerProvider.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModelProvider.observerList.observe(viewLifecycleOwner) {
            if (it) {
                binding!!.recyclerProvider.adapter =
                    ProviderAdapter(viewModelProvider.getListProvider())
                binding!!.recyclerProvider.adapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModelProvider.setListProvider(emptyList())
    }
}