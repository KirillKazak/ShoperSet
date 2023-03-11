package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.R

class ColorProductAdapter:
    RecyclerView.Adapter<ColorProductAdapter.ColorProductViewHolder>() {

    var colorsProductList = arrayListOf<String>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_color, parent, false)
        return ColorProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorProductViewHolder, position: Int) {
        val item= colorsProductList[position]

        holder.ivColorProduct.setBackgroundColor(item.toColorInt())
    }

    override fun getItemCount(): Int = colorsProductList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ColorProductViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val ivColorProduct: ImageView =
            view.findViewById(R.id.iv_color_product)
    }
}