package com.kazak.kirill.shoperset.ui.fragments.home.adapter.flashSale

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemFlashSaleBinding

class FlashSaleHolder(vb: ItemFlashSaleBinding): RecyclerView.ViewHolder(vb.root) {
    val ivImageFlashSaleItem: ImageView = vb.ivImageFlashSaleItem
    val tvSaleFlashSaleItem: TextView = vb.tvSaleFlashSaleItem
    val tvCategoryFlashSaleItem: TextView = vb.tvCategoryFlashSaleItem
    val tvProductNameFlashSaleItem: TextView = vb.tvProductNameFlashSaleItem
    val tvProductPriceFlashSaleItem: TextView = vb.tvProductPriceFlashSaleItem
}