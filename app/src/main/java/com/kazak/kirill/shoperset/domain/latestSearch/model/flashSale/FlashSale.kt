package com.kazak.kirill.shoperset.domain.latestSearch.model.flashSale

data class FlashSale(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)