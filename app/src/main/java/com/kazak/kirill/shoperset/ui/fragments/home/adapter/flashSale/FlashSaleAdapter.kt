package com.kazak.kirill.shoperset.ui.fragments.home.adapter.flashSale

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemFlashSaleBinding
import com.kazak.kirill.domain.products.model.flashSale.FlashSale
import com.kazak.kirill.shoperset.ui.extenshions.loadImageWithoutPlaceholder
import com.kazak.kirill.shoperset.ui.fragments.Constants.DOLLARS
import com.kazak.kirill.shoperset.ui.fragments.Constants.PERCENTS_OFF

class FlashSaleAdapter: RecyclerView.Adapter<FlashSaleHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    var flashSaleList = mutableListOf<FlashSale>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FlashSaleHolder(
        ItemFlashSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlashSaleHolder, position: Int) {
        val item= flashSaleList[position]

        holder.ivImageFlashSaleItem.loadImageWithoutPlaceholder(item.image_url)
        holder.tvSaleFlashSaleItem.text = item.discount.toString() + PERCENTS_OFF
        holder.tvCategoryFlashSaleItem.text = item.category
        holder.tvProductNameFlashSaleItem.text = item.name
        holder.tvProductPriceFlashSaleItem.text = DOLLARS + item.price.toString()

        holder.ivImageFlashSaleItem.setOnClickListener {
            onItemClickListener?.onItemClick()
        }
    }

    override fun getItemCount(): Int = flashSaleList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickListener{
        fun onItemClick()
    }
}
