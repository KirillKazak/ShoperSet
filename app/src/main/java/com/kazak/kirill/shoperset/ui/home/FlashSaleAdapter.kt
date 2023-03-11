package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.latestSearch.model.flashSale.FlashSale

class FlashSaleAdapter:
    RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    var flashSaleList = listOf<FlashSale>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flash_sale, parent, false)
        return FlashSaleViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val item= flashSaleList[position]

        Glide.with(holder.ivImageFlashSaleItem)
            .load(item.image_url)
            .into(holder.ivImageFlashSaleItem)

        holder.tvSaleFlashSaleItem.text = item.discount.toString() + "% off"
        holder.tvCategoryFlashSaleItem.text = item.category
        holder.tvProductNameFlashSaleItem.text = item.name
        holder.tvProductPriceFlashSaleItem.text = "$ " + item.price.toString()

        holder.ivImageFlashSaleItem.setOnClickListener {
            onItemClickListener?.onItemClick()
        }
    }

    override fun getItemCount(): Int = flashSaleList.size

    class FlashSaleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImageFlashSaleItem: ImageView = view.findViewById(R.id.iv_image_flash_sale_item)
        val tvSaleFlashSaleItem: TextView = view.findViewById(R.id.tv_sale_flash_sale_item)
        val tvCategoryFlashSaleItem: TextView = view.findViewById(R.id.tv_category_flash_sale_item)
        val tvProductNameFlashSaleItem: TextView = view.findViewById(R.id.tv_product_name_flash_sale_item)
        val tvProductPriceFlashSaleItem: TextView = view.findViewById(R.id.tv_product_price_flash_sale_item)
    }

    interface OnItemClickListener{
        fun onItemClick()
    }
}