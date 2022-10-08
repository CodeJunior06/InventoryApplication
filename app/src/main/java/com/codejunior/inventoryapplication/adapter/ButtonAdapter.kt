package com.codejunior.inventoryapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codejunior.inventoryapplication.databinding.ItemButtonBinding

data class ButtonData(val label: String, val img: Int, val onClick: (View) -> Unit)

class ButtonAdapter(
    private val listButtons: List<ButtonData>,
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {
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
        fun bind(buttonData: ButtonData) {
            binding.label.text = buttonData.label
            binding.icon.setImageResource(buttonData.img)
            binding.root.setOnClickListener(buttonData.onClick)
        }
    }
}