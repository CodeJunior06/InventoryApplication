package com.codejunior.inventoryapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ItemKardexBinding
import com.codejunior.inventoryapplication.model.db.network.model.Kardex

class KardexAdapter(
    private val arrayKardex:ArrayList<Kardex>
) : RecyclerView.Adapter<KardexAdapter.KardexHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KardexHolder {
        val inflater = LayoutInflater.from(parent.context)
        return KardexHolder(
            ItemKardexBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: KardexHolder, position: Int) {

        holder.render(arrayKardex[position])

    }

    override fun getItemCount(): Int = arrayKardex.size

    inner class KardexHolder(private val binding: ItemKardexBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(kardexModel:Kardex){
            binding.kardexName.text = kardexModel.kardexNameProcess
            binding.kardexDescription.text = kardexModel.kardexDescription
            binding.kardexDate.text = kardexModel.kardexDate

        }

    }
}