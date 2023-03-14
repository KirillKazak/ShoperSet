package com.kazak.kirill.shoperset.ui.fragments.product.additionalPhoto

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemAdditionalPhotoProductBinding

class AdditionalPhotoHolder(vb: ItemAdditionalPhotoProductBinding):
    RecyclerView.ViewHolder(vb.root) {
    val ivAdditionalPhotoProduct: ImageView = vb.ivAdditionalPhotoProduct
    val additionalPhotoItem: CardView = vb.additionalPhotoItem
}