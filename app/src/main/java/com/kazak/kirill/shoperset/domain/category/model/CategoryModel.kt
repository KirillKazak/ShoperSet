package com.kazak.kirill.shoperset.domain.category.model

import android.graphics.drawable.Drawable

data class CategoryModel(
    val categoryName: String,
    val categoryImage: Drawable?,
    val categoryBackground: Drawable?,
    val categoryIsClick: Boolean
)
