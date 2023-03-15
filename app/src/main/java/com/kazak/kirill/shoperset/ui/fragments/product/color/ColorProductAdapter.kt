package com.kazak.kirill.shoperset.ui.fragments.product.color

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemProductColorBinding
import com.kazak.kirill.shoperset.ui.decreaseItem
import com.kazak.kirill.shoperset.ui.increaseItem
import com.kazak.kirill.shoperset.ui.objects.ColorModel

class ColorProductAdapter: RecyclerView.Adapter<ColorHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ColorHolder(
        ItemProductColorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ColorHolder, position: Int) {
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

    interface OnColorItemClickListener {
        fun onColorItemClick(color: String)
    }
}