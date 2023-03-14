package com.kazak.kirill.shoperset.ui.fragments.product.mainPhoto

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemMainPhotoProductBinding

class MainPhotoHolder(vb: ItemMainPhotoProductBinding) :
    RecyclerView.ViewHolder(vb.root) {

    val ivMainPhotoProduct: ImageView = vb.ivMainPhotoProduct
}