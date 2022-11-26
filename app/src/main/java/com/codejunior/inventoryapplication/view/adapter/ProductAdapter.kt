package com.codejunior.inventoryapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ItemProductBinding
import com.codejunior.inventoryapplication.model.db.network.model.Product

class ProductAdapter constructor(private var lstProduct:ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProductHolder(ItemProductBinding.inflate(inflater!!,parent,false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(lstProduct[position])
    }

    override fun getItemCount(): Int = lstProduct.size

    inner class ProductHolder( private val view: ItemProductBinding): RecyclerView.ViewHolder(view.root){
        fun bind(product: Product){
            view.tvName.text = product.productName
            view.tvDisponibilidad.text = product.productAvailability.toString()
            view.tvStock.text = product.productStock.toString()
            view.tvTotal.text = product.productTotal.toString()
            Glide.with(view.imageProduct.context).load(product.productPhoto).placeholder(R.drawable.logo).error(R.drawable.logo).into(view.imageProduct)

        }
    }
}