package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.AdditionalPhotosProductModel

class MainPhotoProductAdapter: RecyclerView.Adapter<MainPhotoProductAdapter.MainPhotoProductViewHolder>() {

    var mainPhotoList = mutableListOf<AdditionalPhotosProductModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<AdditionalPhotosProductModel>) {
        if (mainPhotoList != data) {
            mainPhotoList.clear()
            mainPhotoList.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPhotoProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_photo_product, parent, false)
        return MainPhotoProductViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MainPhotoProductViewHolder, position: Int) {
        val item = mainPhotoList[position]

            Glide.with(holder.ivMainPhotoProduct)
                .load(item.imgUrl)
                .into(holder.ivMainPhotoProduct)
    }

    override fun getItemCount(): Int = mainPhotoList.size

    class MainPhotoProductViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val ivMainPhotoProduct: ImageView =
            view.findViewById(R.id.iv_main_photo_product)
    }
}