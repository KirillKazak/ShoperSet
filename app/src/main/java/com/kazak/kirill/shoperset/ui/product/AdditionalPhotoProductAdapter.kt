package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R

class AdditionalPhotoProductAdapter:
    RecyclerView.Adapter<AdditionalPhotoProductAdapter.AdditionalPhotoViewHolder>() {

    var additionalPhotoList = arrayListOf<String>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalPhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_additional_photo_product, parent, false)
        return AdditionalPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdditionalPhotoViewHolder, position: Int) {
        val item= additionalPhotoList[position]

        Glide.with(holder.ivAdditionalPhotoProduct)
            .load(item)
            .into(holder.ivAdditionalPhotoProduct)
    }

    override fun getItemCount(): Int = additionalPhotoList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class AdditionalPhotoViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val ivAdditionalPhotoProduct: ImageView =
            view.findViewById(R.id.iv_additional_photo_product)
    }
}