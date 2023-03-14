package com.kazak.kirill.shoperset.ui.fragments.product.color

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemProductColorBinding

class ColorHolder(vb: ItemProductColorBinding): RecyclerView.ViewHolder(vb.root) {
    val ivColorProduct: ImageView = vb.ivColorProduct
    val colorItem: CardView = vb.colorItem
}