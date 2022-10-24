package com.codejunior.inventoryapplication.view.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ItemProviderBinding
import com.codejunior.inventoryapplication.model.db.model.Provider

class ProviderHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProviderBinding.bind(view)

    fun render(provider: Provider) {
        binding.tvNameProviders.text = provider.nameProvider
        binding.tvEmailProvider.text = provider.emailProvider
        binding.tvTelefonoProvider.text = provider.phoneProvider
    }
}