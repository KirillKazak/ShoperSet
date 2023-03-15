package com.kazak.kirill.shoperset.ui.fragments.home.adapter.category

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.ItemCategoryBinding
import com.kazak.kirill.shoperset.ui.objects.CategoryModel

class CategoryAdapter(context: Context):
    RecyclerView.Adapter<CategoryHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
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

    interface OnCategoryItemClickListener {
        fun onCategoryItemClick(activeCategories: List<String>)
    }
}