package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.products.model.latestSearch.LatestModel

class LatestSearchAdapter: RecyclerView.Adapter<LatestSearchAdapter.LatestSearchViewHolder>() {
    var onItemClickListener: OnItemClickListener? = null

    var latestSearchList = listOf<LatestModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_latest, parent, false)
        return LatestSearchViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LatestSearchViewHolder, position: Int) {
        val item= latestSearchList[position]

        Glide.with(holder.ivImageLatestItem)
            .load(item.image_url)
            .into(holder.ivImageLatestItem)
        holder.tvCategoryLatestItem.text = item.category
        holder.tvProductNameLatestItem.text = item.name
        holder.tvProductPriceLatestItem.text = "$ " + item.price.toString()

        holder.ivImageLatestItem.setOnClickListener {
            onItemClickListener?.onItemClick()
        }
    }

    override fun getItemCount(): Int = latestSearchList.size

    class LatestSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivImageLatestItem: ImageView = view.findViewById(R.id.iv_image_latest_item)
        val tvCategoryLatestItem: TextView = view.findViewById(R.id.tv_category_latest_item)
        val tvProductNameLatestItem: TextView = view.findViewById(R.id.tv_product_name_latest_item)
        val tvProductPriceLatestItem: TextView = view.findViewById(R.id.tv_product_price_latest_item)
    }

    interface OnItemClickListener{
        fun onItemClick()
    }
}