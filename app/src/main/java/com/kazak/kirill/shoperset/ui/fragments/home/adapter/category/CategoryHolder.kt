package com.kazak.kirill.shoperset.ui.fragments.home.adapter.category

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemCategoryBinding

class CategoryHolder(vb: ItemCategoryBinding) :
    RecyclerView.ViewHolder(vb.root) {

    val greyCircleCategory: ImageView = vb.greyCircleCategory
    val ivImageCategory: ImageView = vb.ivImageCategory
    val tvTitleCategory: TextView = vb.tvTitleCategory
}