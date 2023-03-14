package com.kazak.kirill.shoperset.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.domain.CategoryModel

class CategoryAdapter(context: Context):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var onCategoryItemClickListener: OnCategoryItemClickListener? = null

    private val defaultListCategory = arrayListOf(
        CategoryModel(R.drawable.icon_phone, context.getString(R.string.phones), true),
        CategoryModel(R.drawable.icon_headphones, context.getString(R.string.headphones), true),
        CategoryModel(R.drawable.icon_games, context.getString(R.string.games), true),
        CategoryModel(R.drawable.icon_cars, context.getString(R.string.cars), true),
        CategoryModel(R.drawable.icon_furniture, context.getString(R.string.furniture), true),
        CategoryModel(R.drawable.icon_kids, context.getString(R.string.kids), true)
    )

    var categoryList = defaultListCategory

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item= categoryList[position]

        holder.ivImageCategory.setBackgroundResource(item.categoryImage)
        holder.tvTitleCategory.text = item.categoryName

        if (item.isSelected) {
            holder.greyCircleCategory.setBackgroundResource(R.drawable.grey_circle_active)
        } else {
            holder.greyCircleCategory.setBackgroundResource(R.drawable.grey_circle)
        }

        holder.ivImageCategory.setOnClickListener {

            categoryList.forEach {
                if (it.categoryName == categoryList[position].categoryName) {
                    it.isSelected = !it.isSelected
                }
            }

            val selectedCategories = mutableListOf<String>()
            categoryList.filter { it.isSelected }.map { selectedCategory ->
                selectedCategories.add(selectedCategory.categoryName)
            }

            notifyDataSetChanged()
            onCategoryItemClickListener?.onCategoryItemClick(selectedCategories)
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

    interface OnCategoryItemClickListener {
        fun onCategoryItemClick(activeCategories: List<String>)
    }
}