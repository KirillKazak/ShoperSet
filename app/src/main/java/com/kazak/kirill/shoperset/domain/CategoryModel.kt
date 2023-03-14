package com.kazak.kirill.shoperset.domain

data class CategoryModel(
    val categoryImage: Int,
    val categoryName: String,
    var isSelected: Boolean
                    )