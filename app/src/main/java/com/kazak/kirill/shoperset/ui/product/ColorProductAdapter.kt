package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.ColorModel
import com.kazak.kirill.shoperset.util.decreaseItem
import com.kazak.kirill.shoperset.util.increaseItem

class ColorProductAdapter:
    RecyclerView.Adapter<ColorProductAdapter.ColorProductViewHolder>() {

    var onColorItemClickListener: OnColorItemClickListener? = null

    var colorsProductList = arrayListOf<ColorModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<ColorModel>) {
        if (colorsProductList != data) {
            colorsProductList.clear()
            colorsProductList.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_color, parent, false)
        return ColorProductViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ColorProductViewHolder, position: Int) {
        val item= colorsProductList[position]

        holder.ivColorProduct.setBackgroundColor(item.color.toColorInt())

        if (item.isSelected) {
            increaseItem(holder.colorItem)
        } else {
            decreaseItem(holder.colorItem)
        }

        holder.colorItem.setOnClickListener {
            colorsProductList.forEach {
                it.isSelected = false
            }
            colorsProductList.find { it.id == item.id }?.isSelected = true
            notifyDataSetChanged()

            onColorItemClickListener?.onColorItemClick(item.color)
        }
    }

    override fun getItemCount(): Int = colorsProductList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ColorProductViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val ivColorProduct: ImageView = view.findViewById(R.id.iv_color_product)
        val colorItem: CardView = view.findViewById(R.id.color_item)
    }

    interface OnColorItemClickListener {
        fun onColorItemClick(color: String)
    }
}