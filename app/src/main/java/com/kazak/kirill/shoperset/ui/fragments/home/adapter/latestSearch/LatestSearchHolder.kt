package com.kazak.kirill.shoperset.ui.fragments.home.adapter.latestSearch

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemLatestBinding

class LatestSearchHolder(vb: ItemLatestBinding): RecyclerView.ViewHolder(vb.root)  {

    val ivImageLatestItem: ImageView = vb.ivImageLatestItem
    val tvCategoryLatestItem: TextView = vb.tvCategoryLatestItem
    val tvProductNameLatestItem: TextView = vb.tvProductNameLatestItem
    val tvProductPriceLatestItem: TextView = vb.tvProductPriceLatestItem
}