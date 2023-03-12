package com.kazak.kirill.shoperset.domain

data class Category(
    val categoryImage: Int,
    val categoryName: String,
    var isSelected: Boolean
                    )