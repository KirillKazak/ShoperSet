package com.kazak.kirill.shoperset.data.category.entity

import android.graphics.drawable.Drawable

data class CategoryEntity(
        val categoryName: String,
        val categoryImage: Drawable?,
        val categoryBackground: Drawable?,
        val categoryIsClick: Boolean
        )