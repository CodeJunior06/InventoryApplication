package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.FragmentAddProvidersBinding
import com.codejunior.inventoryapplication.utils.extension.intentProviderToMain
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.NAVIGATION
import com.codejunior.inventoryapplication.viewmodel.ProveedoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAddProviders : Fragment() {
    private lateinit var _binding:FragmentAddProvidersBinding
    val binding:FragmentAddProvidersBinding get() = _binding

    private val viewModelAddProviders:ProveedoresViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddProvidersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { connectoViewModel() }

        viewModelAddProviders.navigation.observe(viewLifecycleOwner){
            when(it){
                NAVIGATION.GO_MAIN_VIEW-> startActivity(intentProviderToMain())
            }
        }
        viewModelAddProviders.errores.observe(viewLifecycleOwner){
            toastMessage("EMPTY FIELD")

        }
        viewModelAddProviders.success.observe(viewLifecycleOwner){

        }

    }

    private fun connectoViewModel() {
        val lst = mutableListOf(binding.edtName.editText?.text.toString(),
            binding.edtProvider.editText?.text.toString(),
            binding.edtNitProvider.editText?.text.toString(),
            binding.edtTelefonoProvider.editText?.text.toString(),
            binding.edtEmailProvider.editText?.text.toString(),
            binding.edtAddress.editText?.text.toString()
        )
        viewModelAddProviders.getDataView(lst)
    }

}