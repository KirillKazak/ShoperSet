package com.kazak.kirill.shoperset.domain.product

data class ColorModel (
    val id: Int,
    val color: String,
    var isSelected: Boolean
        )