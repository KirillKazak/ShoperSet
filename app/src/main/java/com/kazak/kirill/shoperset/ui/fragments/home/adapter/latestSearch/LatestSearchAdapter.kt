package com.kazak.kirill.shoperset.ui.fragments.home.adapter.latestSearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.databinding.ItemLatestBinding
import com.kazak.kirill.domain.products.model.latestSearch.LatestModel
import com.kazak.kirill.shoperset.ui.extenshions.loadImageWithoutPlaceholder
import com.kazak.kirill.shoperset.ui.fragments.Constants.DOLLARS

class LatestSearchAdapter: RecyclerView.Adapter<LatestSearchHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    var latestSearchList = listOf<LatestModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LatestSearchHolder (
        ItemLatestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LatestSearchHolder, position: Int) {
        val item= latestSearchList[position]

        holder.ivImageLatestItem.loadImageWithoutPlaceholder(item.image_url)
        holder.tvCategoryLatestItem.text = item.category
        holder.tvProductNameLatestItem.text = item.name
        holder.tvProductPriceLatestItem.text = DOLLARS + item.price.toString()

        holder.ivImageLatestItem.setOnClickListener {
            onItemClickListener?.onItemClick()
        }
    }

    override fun getItemCount(): Int = latestSearchList.size

    interface OnItemClickListener{
        fun onItemClick()
    }
}