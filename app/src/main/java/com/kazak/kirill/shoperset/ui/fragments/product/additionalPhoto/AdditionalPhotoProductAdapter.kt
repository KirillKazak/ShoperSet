package com.kazak.kirill.shoperset.ui.fragments.product.additionalPhoto

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemAdditionalPhotoProductBinding
import com.kazak.kirill.shoperset.ui.decreaseItem
import com.kazak.kirill.shoperset.ui.extenshions.loadImageWithoutPlaceholder
import com.kazak.kirill.shoperset.ui.increaseItem
import com.kazak.kirill.shoperset.ui.objects.AdditionalPhotosProductModel


class AdditionalPhotoProductAdapter:
    RecyclerView.Adapter<AdditionalPhotoHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdditionalPhotoHolder(
        ItemAdditionalPhotoProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AdditionalPhotoHolder, position: Int) {
        val item= additionalPhotoList[position]

        holder.ivAdditionalPhotoProduct.loadImageWithoutPlaceholder(item.imgUrl)

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

    interface OnPhotoItemClickListener {
        fun onPhotoItemClick(id: Int)
    }
}