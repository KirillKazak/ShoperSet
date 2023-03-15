package com.kazak.kirill.shoperset.ui.fragments.product.mainPhoto

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemMainPhotoProductBinding
import com.kazak.kirill.shoperset.ui.extenshions.loadImageWithoutPlaceholder
import com.kazak.kirill.shoperset.ui.objects.AdditionalPhotosProductModel

class MainPhotoProductAdapter: RecyclerView.Adapter<MainPhotoHolder>() {

    var mainPhotoList = mutableListOf<AdditionalPhotosProductModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<AdditionalPhotosProductModel>) {
        if (mainPhotoList != data) {
            mainPhotoList.clear()
            mainPhotoList.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainPhotoHolder(
        ItemMainPhotoProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MainPhotoHolder, position: Int) {
        val item = mainPhotoList[position]

        holder.ivMainPhotoProduct.loadImageWithoutPlaceholder(item.imgUrl)
    }

    override fun getItemCount(): Int = mainPhotoList.size
}