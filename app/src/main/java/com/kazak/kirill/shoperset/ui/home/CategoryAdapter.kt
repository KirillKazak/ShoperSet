package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.R

class CategoryAdapter:
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var onCategoryItemClickListener: OnCategoryItemClickListener? = null

    var categoryList = arrayListOf<Category>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item= categoryList[position]

        holder.ivImageCategory.setBackgroundResource(item.categoryImage)
        holder.tvTitleCategory.text = item.categoryName

        if (item.isClick) {
            holder.greyCircleCategory.setBackgroundResource(R.drawable.grey_circle_active)
        } else {
            holder.greyCircleCategory.setBackgroundResource(R.drawable.grey_circle)
        }

        holder.ivImageCategory.setOnClickListener {

            onCategoryItemClickListener?.onCategoryItemClick(position)
            Log.d("POSITIONN", position.toString())
        }
    }

    override fun getItemCount(): Int = categoryList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class CategoryViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val greyCircleCategory: ImageView = view.findViewById(R.id.grey_circle_category)
        val ivImageCategory: ImageView = view.findViewById(R.id.iv_image_category)
        val tvTitleCategory: TextView = view.findViewById(R.id.tv_title_category)
    }

    class Category (
        val categoryImage: Int,
        val categoryName: String,
        var isClick: Boolean
    )

    interface OnCategoryItemClickListener {
        fun onCategoryItemClick(position: Int)
    }
}