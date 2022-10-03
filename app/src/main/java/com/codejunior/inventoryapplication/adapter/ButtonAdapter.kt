package com.codejunior.inventoryapplication.adapter

import android.graphics.drawable.Icon
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ItemButtonBinding

data class ButtonData(val label: String, val img: Int)

class ButtonAdapter(private val listButtons: List<ButtonData>) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ButtonViewHolder(
            ItemButtonBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(listButtons[position])
    }

    override fun getItemCount() = listButtons.size

    inner class ButtonViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ButtonData) {
            binding.label.text = item.label
            binding.icon.setImageResource(item.img)
        }
    }
}