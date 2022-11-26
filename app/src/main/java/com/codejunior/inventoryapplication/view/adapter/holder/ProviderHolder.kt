package com.codejunior.inventoryapplication.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ItemProviderBinding
import com.codejunior.inventoryapplication.model.db.network.model.Provider

class ProviderHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProviderBinding.bind(view)

    fun render(provider: Provider) {
        binding.tvNameProviders.text = provider.providerName
        binding.tvPhoneProvider.text = provider.providerPhone
        binding.tvCompanyProvider.text = provider.providerCompany
    }
}