package com.codejunior.inventoryapplication.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ItemKardexBinding
import com.codejunior.inventoryapplication.model.db.network.model.Kardex
import com.codejunior.inventoryapplication.utils.Utilities
import dagger.hilt.android.qualifiers.ApplicationContext
import android.graphics.drawable.GradientDrawable




class KardexAdapter(
    private val arrayKardex:ArrayList<Kardex>
)  : RecyclerView.Adapter<KardexAdapter.KardexHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KardexHolder {
        context = parent.context
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
            //binding.linearKardex.setBackgroundColor(Utilities.getColorContainerKardex(kardexModel.kardexTypeModule,context))
            val shape = GradientDrawable()
            shape.cornerRadius = 20f
            shape.setColor(Utilities.getColorContainerKardex(kardexModel.kardexTypeModule,context))
            binding.linearKardex.background = shape

        }

    }
}