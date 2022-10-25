package com.codejunior.inventoryapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.model.db.network.model.Provider
import com.codejunior.inventoryapplication.view.adapter.holder.ProviderHolder

class ProviderAdapter(
    private val modelProvider: List<Provider>
) : RecyclerView.Adapter<ProviderHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProviderHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProviderHolder(layoutInflater.inflate(R.layout.item_provider, parent, false))
    }

    override fun onBindViewHolder(holder: ProviderHolder, position: Int) {
        val positionModel = modelProvider[position]
        holder.render(positionModel)
    }

    override fun getItemCount(): Int = modelProvider.size


}