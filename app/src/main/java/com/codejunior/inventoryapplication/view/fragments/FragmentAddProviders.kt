package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.codejunior.inventoryapplication.databinding.FragmentAddProvidersBinding
import com.codejunior.inventoryapplication.utils.extension.intentMainFromFragment
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.Navigation
import com.codejunior.inventoryapplication.viewmodel.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Singleton

@AndroidEntryPoint
@Singleton
class FragmentAddProviders : Fragment() {
    private var _binding: FragmentAddProvidersBinding? = null
    val binding get() = _binding

    private val viewModelAddProviders: ProviderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddProvidersBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.button.setOnClickListener { connectToViewModel() }

        viewModelAddProviders.navigation.observe(viewLifecycleOwner) {
            when (it) {
                Navigation.GO_MAIN_VIEW -> startActivity(intentMainFromFragment())
                else -> println()
            }
        }
        viewModelAddProviders.error.observe(viewLifecycleOwner) {
            toastMessage(it)

        }
        viewModelAddProviders.success.observe(viewLifecycleOwner) {
            toastMessage(it)
        }
    }

    private fun connectToViewModel() {
        val lst = mutableListOf(
            binding!!.edtName.editText?.text.toString(),
            binding!!.edtProvider.editText?.text.toString(),
            binding!!.edtNitProvider.editText?.text.toString(),
            binding!!.edtTelefonoProvider.editText?.text.toString(),
            binding!!.edtEmailProvider.editText?.text.toString(),
            binding!!.edtAddress.editText?.text.toString()
        )
        viewModelAddProviders.getDataView(lst)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}