package com.kazak.kirill.shoperset.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.AdditionalPhotosProductModel
import com.kazak.kirill.shoperset.util.decreaseItem
import com.kazak.kirill.shoperset.util.increaseItem


class AdditionalPhotoProductAdapter:
    RecyclerView.Adapter<AdditionalPhotoProductAdapter.AdditionalPhotoViewHolder>() {

    var onPhotoItemClickListener: OnPhotoItemClickListener? = null

    var additionalPhotoList = mutableListOf<AdditionalPhotosProductModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<AdditionalPhotosProductModel>) {
        if (additionalPhotoList != data) {
            additionalPhotoList.clear()
            additionalPhotoList.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalPhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_additional_photo_product, parent, false)
        return AdditionalPhotoViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdditionalPhotoViewHolder, position: Int) {
        val item= additionalPhotoList[position]

        Glide.with(holder.ivAdditionalPhotoProduct)
            .load(item.imgUrl)
            .into(holder.ivAdditionalPhotoProduct)

        if (item.isSelected) {
            increaseItem(holder.additionalPhotoItem)
        } else {
            decreaseItem(holder.additionalPhotoItem)
        }

        holder.ivAdditionalPhotoProduct.setOnClickListener {
            additionalPhotoList.forEach {
                it.isSelected = false
            }
            additionalPhotoList.find { it.id == additionalPhotoList[position].id }?.isSelected = true
            notifyDataSetChanged()

            onPhotoItemClickListener?.onPhotoItemClick(item.id)
        }
    }

    override fun getItemCount(): Int = additionalPhotoList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class AdditionalPhotoViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val ivAdditionalPhotoProduct: ImageView = view.findViewById(R.id.iv_additional_photo_product)
        val additionalPhotoItem: CardView = view.findViewById(R.id.additional_photo_item)
    }

    interface OnPhotoItemClickListener {
        fun onPhotoItemClick(id: Int)
    }
}